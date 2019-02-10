package pl.damian.zamawiam.order.orderPack.orderMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.order.orderPack.OrderPack;
import pl.damian.zamawiam.order.orderPack.OrderPackRepository;

import java.util.Optional;

@Component
public class OrderMenuMapper implements Mapper<OrderMenu, OrderMenuDto> {

    @Autowired
    OrderPackRepository orderPackRepository;

    public OrderMenu toEntity(OrderMenuDto dto) {
        OrderMenu entity = new OrderMenu();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setComment(dto.getComment());
        entity.setPrice(dto.getPrice());
        Optional<OrderPack> orderPack = orderPackRepository.findById(dto.getId());
        orderPack.ifPresent(entity::setOrderPack);
        return entity;
    }

    public OrderMenuDto toDto(OrderMenu entity) {
        OrderMenuDto dto = new OrderMenuDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setComment(entity.getComment());
        dto.setPrice(entity.getPrice());
        entity.getOrderPack().getId();
        return dto;
    }
}
