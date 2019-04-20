package pl.damian.zamawiam.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.order.OrderHead;
import pl.damian.zamawiam.repo.model.order.OrderItem;
import pl.damian.zamawiam.repo.model.order.OrderPack;
import pl.damian.zamawiam.repo.model.order.Payment;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.order.OrderItemRepository;
import pl.damian.zamawiam.repo.repository.order.OrderPackRepository;
import pl.damian.zamawiam.repo.repository.security.UserRepository;
import pl.damian.zamawiam.service.dto.order.OrderHeadDTO;
import pl.damian.zamawiam.service.dto.order.OrderItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderHeadMapper extends GenericMapper<OrderHead, OrderHeadDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Autowired
    private GenericMapper<OrderItem, OrderItemDTO> orderItemMapper;

    @Override
    protected OrderHead initEntity() {
        return new OrderHead();
    }

    @Override
    protected OrderHeadDTO initDTO() {
        return new OrderHeadDTO();
    }

    @Override
    protected void mapEntityToDTO(OrderHead orderHead, OrderHeadDTO orderHeadDto) {
        orderHeadDto.setId(orderHead.getId());
        orderHeadDto.setOrderPackId(orderHead.getOrderPack().getId());
        orderHeadDto.setUserId(orderHead.getUser().getId());
        orderHeadDto.setUserName(orderHead.getUser().getEmail());
        orderHeadDto.setComment(orderHead.getComment());
        orderHeadDto.setPayment(orderHead.getPayment().name());
        if (orderHead.getOrderItems() != null){
            orderHeadDto.setOrderItems(orderItemMapper.toDTO(orderHead.getOrderItems().stream()
                    .filter(orderItem -> orderItem.getParentOrderItem()==null).collect(Collectors.toList())));
        }
        orderHeadDto.setAmount(orderHead.getAmount());
        orderHeadDto.setPaid(orderHead.getPaid());
        orderHeadDto.setMessage(orderHead.getMessage());
    }

    @Override
    protected void mapDTOToEntity(OrderHeadDTO orderHeadDto, OrderHead orderHead) {
        orderHead.setId(orderHeadDto.getId());

        Optional<OrderPack> orderPack = orderPackRepository.findById(orderHeadDto.getOrderPackId());
        orderPack.ifPresent(orderHead::setOrderPack);

        Optional<User> user = userRepository.findById(orderHeadDto.getUserId());
        user.ifPresent(orderHead::setUser);

        orderHead.setComment(orderHeadDto.getComment());

        orderHead.setPayment(Payment.valueOf(orderHeadDto.getPayment()));

        if (orderHeadDto.getId() != null)
            orderHead.setOrderItems(orderItemMapper.toEntity(orderHeadDto.getOrderItems()));

        orderHead.setAmount(orderHeadDto.getAmount());

        orderHead.setPaid(orderHeadDto.getPaid());

        orderHead.setMessage(orderHeadDto.getMessage());
    }
}
