package pl.damian.zamawiam.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.order.OrderHead;
import pl.damian.zamawiam.repo.model.order.OrderItem;
import pl.damian.zamawiam.repo.model.order.OrderMenu;
import pl.damian.zamawiam.repo.model.restaurant.FoodItem;
import pl.damian.zamawiam.repo.repository.order.OrderHeadRepository;
import pl.damian.zamawiam.repo.repository.order.OrderMenuRepository;
import pl.damian.zamawiam.repo.repository.restaurant.FoodItemRepository;
import pl.damian.zamawiam.service.dto.order.OrderItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.Optional;

@Component
public class OrderItemMapper extends GenericMapper<OrderItem, OrderItemDTO> {

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    @Autowired
    private OrderMenuRepository orderMenuRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    protected OrderItem initEntity() {
        return new OrderItem();
    }

    @Override
    protected OrderItemDTO initDTO() {
        return new OrderItemDTO();
    }

    @Override
    protected void mapEntitytoDTO(OrderItem orderItem, OrderItemDTO orderItemDto) {
        orderItemDto.setId(orderItem.getId());

        orderItemDto.setOrderHeadId(orderItem.getOrderHead().getId());

        orderItemDto.setAmount(orderItem.getAmount());

        orderItemDto.setOwnOrder(orderItem.getOwnOrder());

        if (orderItem.getFoodItem() != null)
            orderItemDto.setFoodItemId(orderItem.getFoodItem().getId());

        if (orderItem.getOrderMenu() != null)
            orderItemDto.setOrderMenuId(orderItem.getOrderMenu().getId());
    }

    @Override
    protected void mapDTOToEntity(OrderItemDTO orderItemDto, OrderItem orderItem) {
        orderItem.setId(orderItemDto.getId());

        Optional<OrderHead> orderHead = orderHeadRepository.findById(orderItemDto.getOrderHeadId());
        orderHead.ifPresent(orderItem::setOrderHead);

        orderItem.setAmount(orderItemDto.getAmount());

        orderItem.setOwnOrder(orderItemDto.getOwnOrder());

        if(orderItemDto.getFoodItemId() != null){
            Optional<FoodItem> foodItem = foodItemRepository.findById(orderItemDto.getFoodItemId());
            foodItem.ifPresent(orderItem::setFoodItem);
        }

        if(orderItemDto.getOrderMenuId() != null){
            Optional<OrderMenu> orderMenu = orderMenuRepository.findById(orderItemDto.getOrderMenuId());
            orderMenu.ifPresent(orderItem::setOrderMenu);
        }
    }
}
