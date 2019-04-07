package pl.damian.zamawiam.order.orderPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenu;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenuMapper;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenuRepository;
import pl.damian.zamawiam.order.orderPack.orderStatus.OrderStatusRepository;
import pl.damian.zamawiam.security.auth.AuthenticationFacade;
import pl.damian.zamawiam.security.user.User;
import pl.damian.zamawiam.security.user.UserDetailsImpl;
import pl.damian.zamawiam.security.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderPackService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    @Autowired
    private OrderMenuMapper orderMenuMapper;

    @Autowired
    private OrderPackMapper orderPackMapper;

    @Autowired
    private UserRepository userRepository;

    public List<OrderPackDto> findAllByUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.getOne(userDetails.getId());
        return orderPackRepository.findByUser(user).stream().map(orderPackMapper::toDto).collect(Collectors.toList());
    }

    public Optional<OrderPackDto> findById(Long id){
        return orderPackRepository.findById(id).map(orderPackMapper::toDto);
    }

    public OrderPackDto create(OrderPackDto dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        dto.setUserId(userDetails.getId());
        dto.setOrderStatusId(orderStatusRepository.findFirstByOrderBySequenceAsc().getId());
        dto.setStatusChanged(LocalDateTime.now());
        dto.setCreated(LocalDateTime.now());
        OrderPackDto orderPackDto = saveOrderPackDto(dto);
        if (dto.getOrderMenus() != null && dto.getOrderMenus().size() > 0) {
            orderPackDto.setOrderMenus(dto.getOrderMenus().stream().map(orderMenuDto -> {
                orderMenuDto.setOrderPackId(orderPackDto.getId());
                OrderMenu entity = orderMenuMapper.toEntity(orderMenuDto);
                OrderMenu entitySaved = orderMenuRepository.save(entity);
                return orderMenuMapper.toDto(entitySaved);
            }).collect(Collectors.toList()));
        }
        return orderPackDto;
    }

    public OrderPackDto update(OrderPackDto dto) {
        return saveOrderPackDto(dto);
    }

    private OrderPackDto saveOrderPackDto(OrderPackDto dto) {
        OrderPack orderPack = orderPackMapper.toEntity(dto);
        OrderPack orderPackSaved = orderPackRepository.save(orderPack);
        return orderPackMapper.toDto(orderPackSaved);
    }
}
