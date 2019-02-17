package pl.damian.zamawiam.order.orderPack.orderHead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.order.orderPack.OrderPack;
import pl.damian.zamawiam.order.orderPack.OrderPackRepository;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItemMapper;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItemRepository;
import pl.damian.zamawiam.order.orderPack.orderHead.payment.Payment;
import pl.damian.zamawiam.security.user.User;
import pl.damian.zamawiam.security.user.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderHeadMapper implements Mapper<OrderHead, OrderHeadDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Override
    public OrderHeadDto toDto(OrderHead entity) {
        OrderHeadDto dto = new OrderHeadDto();
        dto.setId(entity.getId());
        dto.setOrderPackId(entity.getOrderPack().getId());
        dto.setUserId(entity.getUser().getId());
        dto.setComment(entity.getComment());
        dto.setPayment(entity.getPayment().name());
        dto.setOrderItems(entity.getOrderItems().stream().map(orderItemMapper::toDto).collect(Collectors.toList()));
        dto.setAmount(entity.getAmount());
        dto.setPaid(entity.getPaid());
        return dto;
    }

    @Override
    public OrderHead toEntity(OrderHeadDto dto) {
        OrderHead entity = new OrderHead();
        entity.setId(dto.getId());
        Optional<OrderPack> orderPack = orderPackRepository.findById(dto.getOrderPackId());
        orderPack.ifPresent(entity::setOrderPack);
        Optional<User> user = userRepository.findById(dto.getUserId());
        user.ifPresent(entity::setUser);
        entity.setComment(dto.getComment());
        entity.setPayment(Payment.valueOf(dto.getPayment()));
        entity.setOrderItems(dto.getOrderItems().stream().map(orderItemMapper::toEntity).collect(Collectors.toList()));
        entity.setAmount(dto.getAmount());
        entity.setPaid(dto.getPaid());
        return entity;
    }
}
