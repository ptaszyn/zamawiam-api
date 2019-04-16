package pl.damian.zamawiam.service.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.order.OrderMenu;
import pl.damian.zamawiam.repo.model.order.OrderPack;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.order.OrderMenuRepository;
import pl.damian.zamawiam.repo.repository.order.OrderPackRepository;
import pl.damian.zamawiam.repo.repository.order.OrderStatusRepository;
import pl.damian.zamawiam.repo.repository.security.UserRepository;
import pl.damian.zamawiam.service.dto.order.OrderMenuDTO;
import pl.damian.zamawiam.service.dto.order.OrderPackDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.order.OrderPackService;
import pl.damian.zamawiam.service.service.security.AuthenticationFacade;
import pl.damian.zamawiam.service.service.security.impl.userDetails.UserDetailsImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderPackServiceImpl implements OrderPackService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenericMapper<OrderMenu, OrderMenuDTO> orderMenuMapper;

    @Autowired
    private GenericMapper<OrderPack, OrderPackDTO> orderPackMapper;

    @Override
    public List<OrderPackDTO> findAllByUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        return orderPackMapper.convertToDTO(orderPackRepository.findByUser(user));
    }

    @Override
    public Optional<OrderPackDTO> findById(Long id) {
        return orderPackRepository.findById(id).map(orderPackMapper::convertToDTO);
    }

    @Override
    public OrderPackDTO create(OrderPackDTO dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        dto.setUserId(userDetails.getId());
        dto.setOrderStatusId(orderStatusRepository.findFirstByOrderBySequenceAsc().getId());
        dto.setCreated(LocalDateTime.now());
        OrderPackDTO orderPackDto = saveOrderPackDto(dto);
        if (dto.getOrderMenus() != null && dto.getOrderMenus().size() > 0) {
            orderPackDto.setOrderMenus(dto.getOrderMenus().stream().map(orderMenuDTO -> {
                orderMenuDTO.setOrderPackId(orderPackDto.getId());
                OrderMenu entity = orderMenuMapper.convertToEntity(orderMenuDTO);
                OrderMenu entitySaved = orderMenuRepository.save(entity);
                return orderMenuMapper.convertToDTO(entitySaved);
            }).collect(Collectors.toList()));
        }
        return orderPackDto;
    }

    @Override
    public OrderPackDTO update(OrderPackDTO dto) {
        return saveOrderPackDto(dto);
    }

    private OrderPackDTO saveOrderPackDto(OrderPackDTO dto) {
        dto.setStatusChanged(LocalDateTime.now());
        OrderPack orderPack = orderPackMapper.convertToEntity(dto);
        OrderPack orderPackSaved = orderPackRepository.save(orderPack);
        return orderPackMapper.convertToDTO(orderPackSaved);
    }
}
