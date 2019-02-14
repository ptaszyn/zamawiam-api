package pl.damian.zamawiam.order.orderPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenuMapper;
import pl.damian.zamawiam.restaurant.Restaurant;
import pl.damian.zamawiam.restaurant.RestaurantRepository;
import pl.damian.zamawiam.order.orderPack.orderStatus.OrderStatus;
import pl.damian.zamawiam.order.orderPack.orderStatus.OrderStatusRepository;
import pl.damian.zamawiam.security.user.User;
import pl.damian.zamawiam.security.user.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderPackMapper implements Mapper<OrderPack, OrderPackDto> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderMenuMapper orderMenuMapper;

    @Override
    public OrderPackDto toDto(OrderPack entity) {
        OrderPackDto dto = new OrderPackDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setComment(entity.getComment());
        dto.setMenuSource(entity.getMenuSource());
        dto.setRestaurantId(entity.getRestaurant().getId());
        dto.setOrderStatusId(entity.getOrderStatus().getId());
        dto.setStatusChanged(entity.getStatusChanged());
        dto.setCreated(entity.getCreated());
        if (entity.getOrderMenus() != null)
            dto.setOrderMenus(entity.getOrderMenus().stream().map(orderMenuMapper::toDto).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public OrderPack toEntity(OrderPackDto dto) {
        OrderPack entity = new OrderPack();
        entity.setId(dto.getId());
        Optional<User> user = userRepository.findById(dto.getUserId());
        user.ifPresent(entity::setUser);
        entity.setComment(dto.getComment());
        entity.setMenuSource(dto.getMenuSource());
        Optional<Restaurant> restaurant = restaurantRepository.findById(dto.getRestaurantId());
        restaurant.ifPresent(entity::setRestaurant);
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(dto.getOrderStatusId());
        orderStatus.ifPresent(entity::setOrderStatus);
        entity.setStatusChanged(dto.getStatusChanged());
        entity.setCreated(dto.getCreated());
        if (dto.getOrderMenus() != null && dto.getId() != null)
            entity.setOrderMenus(dto.getOrderMenus().stream().map(orderMenuMapper::toEntity).collect(Collectors.toList()));
        return entity;
    }
}
