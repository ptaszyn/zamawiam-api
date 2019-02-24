package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHead;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHeadDto;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHeadRepository;
import pl.damian.zamawiam.security.auth.AuthenticationFacade;
import pl.damian.zamawiam.security.user.UserDetailsImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    public List<OrderItemDto> getAllByHeadId(Long headId) {
        OrderHead orderHead = orderHeadRepository.findById(headId).get();
        return orderItemRepository.findByOrderHead(orderHead)
                .stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderItemDto> findById(Long id) {
        return orderItemRepository.findById(id).map(orderItemMapper::toDto);
    }

    public OrderItemDto create(OrderItemDto dto) {
        return saveOrderItemDto(dto);
    }

    public OrderItemDto update(OrderItemDto dto) {
        return saveOrderItemDto(dto);
    }

    private OrderItemDto saveOrderItemDto(OrderItemDto dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto);
        OrderItem orderItemSaved = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(orderItemSaved);
    }
}
