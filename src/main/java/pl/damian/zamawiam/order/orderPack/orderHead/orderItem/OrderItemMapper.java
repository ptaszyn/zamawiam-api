package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHead;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHeadRepository;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenu;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenuRepository;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItem;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItemRepository;

import java.util.Optional;

@Component
public class OrderItemMapper implements Mapper<OrderItem, OrderItemDto> {

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public OrderItemDto toDto(OrderItem entity) {
        OrderItemDto dto = new OrderItemDto();

        dto.setId(entity.getId());

        dto.setOrderHeadId(entity.getOrderHead().getId());

        dto.setAmount(entity.getAmount());

        dto.setOwnOrder(entity.getOwnOrder());

        if (entity.getFoodItem() != null)
            dto.setFoodItemId(entity.getFoodItem().getId());

        if (entity.getOrderMenu() != null)
            dto.setOrderMenuId(entity.getOrderMenu().getId());

        return dto;
    }

    @Override
    public OrderItem toEntity(OrderItemDto dto) {
        OrderItem entity = new OrderItem();

        entity.setId(dto.getId());

        Optional<OrderHead> orderHead = orderHeadRepository.findById(dto.getOrderHeadId());
        orderHead.ifPresent(entity::setOrderHead);

        entity.setAmount(dto.getAmount());

        entity.setOwnOrder(dto.getOwnOrder());

        if(dto.getFoodItemId() != null){
            Optional<FoodItem> foodItem = foodItemRepository.findById(dto.getFoodItemId());
            foodItem.ifPresent(entity::setFoodItem);
        }

        if(dto.getOrderMenuId() != null){
            Optional<OrderMenu> orderMenu = orderMenuRepository.findById(dto.getOrderMenuId());
            orderMenu.ifPresent(entity::setOrderMenu);
        }

        return entity;
    }
}
