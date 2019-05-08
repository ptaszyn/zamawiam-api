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
import pl.damian.zamawiam.service.dto.PatchDTO;
import pl.damian.zamawiam.service.dto.order.OrderMenuDTO;
import pl.damian.zamawiam.service.dto.order.OrderPackDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.order.OrderPackService;
import pl.damian.zamawiam.service.service.security.AuthenticationFacade;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
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
    public List<OrderPackDTO> findAll(boolean isOwner) {
        User user = userRepository.getOne(authenticationFacade.getUserId());
        if (isOwner) return orderPackMapper.toDTO(orderPackRepository.findByUser(user));
        return orderPackMapper.toDTO(orderPackRepository.findAll());
    }

    @Override
    public Optional<OrderPackDTO> findById(Long id) {
        return orderPackRepository.findById(id).map(orderPackMapper::toDTO);
    }

    @Override
    public OrderPackDTO create(OrderPackDTO dto) {
        dto.setUserId(authenticationFacade.getUserId());
        dto.setOrderStatusId(orderStatusRepository.findFirstByOrderBySequenceAsc().getId());
        dto.setCreated(LocalDateTime.now());
        OrderPackDTO orderPackDto = saveOrderPackDto(dto);
        if (dto.getOrderMenus() != null && dto.getOrderMenus().size() > 0) {
            orderPackDto.setOrderMenus(dto.getOrderMenus().stream().map(orderMenuDTO -> {
                orderMenuDTO.setOrderPackId(orderPackDto.getId());
                OrderMenu entity = orderMenuMapper.toEntity(orderMenuDTO);
                OrderMenu entitySaved = orderMenuRepository.save(entity);
                return orderMenuMapper.toDTO(entitySaved);
            }).collect(Collectors.toList()));
        }
        return orderPackDto;
    }

    @Override
    public OrderPackDTO update(OrderPackDTO dto) {
        return saveOrderPackDto(dto);
    }

    @Override
    public OrderPackDTO patch(Long id, List<PatchDTO> dto) {
        /*
        OrderPack orderPack = orderPackRepository.getOne(id);
        dto.stream().map(patchDTO -> {
            switch(patchDTO.getField()){
                case "status":
                    @Transactional
                    orderPack.setOrderStatus( patchDTO.getValue());
                    break;
                    default:
                        "erroe";
                        break;

            })
        })
        */
        return null;

    }

    private OrderPackDTO saveOrderPackDto(OrderPackDTO dto) {
        dto.setStatusChanged(LocalDateTime.now());
        OrderPack orderPack = orderPackMapper.toEntity(dto);
        OrderPack orderPackSaved = orderPackRepository.save(orderPack);
        return orderPackMapper.toDTO(orderPackSaved);
    }
}
