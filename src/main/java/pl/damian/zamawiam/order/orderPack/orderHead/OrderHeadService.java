package pl.damian.zamawiam.order.orderPack.orderHead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.order.orderPack.OrderPack;
import pl.damian.zamawiam.order.orderPack.OrderPackRepository;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItem;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItemMapper;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItemRepository;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenu;
import pl.damian.zamawiam.security.auth.AuthenticationFacade;
import pl.damian.zamawiam.security.user.UserDetailsImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHeadService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderHeadMapper orderHeadMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public List<OrderHeadDto> findAllByPackId(Long packId) {
        OrderPack orderPack = orderPackRepository.findById(packId).get();
        return orderHeadRepository.findByOrderPack(orderPack)
                .stream()
                .map(orderHeadMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderHeadDto> findById(Long packId, Long id) {
        return orderHeadRepository.findById(id).map(orderHeadMapper::toDto);
    }

    public OrderHeadDto create(OrderHeadDto dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        dto.setUserId(userDetails.getId());
        OrderHeadDto orderHeadDto = saveOrderHeadDto(dto);
        if (dto.getOrderItems() != null && dto.getOrderItems().size() > 0) {
            orderHeadDto.setOrderItems(dto.getOrderItems().stream().map(orderItemDto -> {
                orderItemDto.setOrderHeadId(orderHeadDto.getId());
                OrderItem entity = orderItemMapper.toEntity(orderItemDto);
                OrderItem entitySaved = orderItemRepository.save(entity);
                return orderItemMapper.toDto(entitySaved);
            }).collect(Collectors.toList()));
        }
        return orderHeadDto;
    }

    public OrderHeadDto update(OrderHeadDto dto) {
        return saveOrderHeadDto(dto);
    }

    private OrderHeadDto saveOrderHeadDto(OrderHeadDto dto) {
        OrderHead orderHead = orderHeadMapper.toEntity(dto);
        OrderHead orderHeadSaved = orderHeadRepository.save(orderHead);
        return orderHeadMapper.toDto(orderHeadSaved);
    }
}
