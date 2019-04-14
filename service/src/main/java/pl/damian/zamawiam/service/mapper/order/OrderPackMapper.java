package pl.damian.zamawiam.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.order.OrderMenu;
import pl.damian.zamawiam.repo.model.order.OrderPack;
import pl.damian.zamawiam.repo.model.order.OrderStatus;
import pl.damian.zamawiam.repo.model.restaurant.Restaurant;
import pl.damian.zamawiam.repo.model.security.User;
import pl.damian.zamawiam.repo.repository.order.OrderStatusRepository;
import pl.damian.zamawiam.repo.repository.restaurant.RestaurantRepository;
import pl.damian.zamawiam.repo.repository.security.UserRepository;
import pl.damian.zamawiam.service.dto.order.OrderMenuDTO;
import pl.damian.zamawiam.service.dto.order.OrderPackDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.Optional;

@Component
public class OrderPackMapper extends GenericMapper<OrderPack, OrderPackDTO> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenericMapper<OrderMenu, OrderMenuDTO> orderMenuMapper;

    @Override
    protected OrderPack initEntity() {
        return new OrderPack();
    }

    @Override
    protected OrderPackDTO initDTO() {
        return new OrderPackDTO();
    }

    @Override
    protected void mapEntitytoDTO(OrderPack orderPack, OrderPackDTO orderPackDto) {
        orderPackDto.setId(orderPack.getId());
        orderPackDto.setUserId(orderPack.getUser().getId());
        orderPackDto.setComment(orderPack.getComment());
        orderPackDto.setMenuSource(orderPack.getMenuSource());
        orderPackDto.setRestaurantId(orderPack.getRestaurant().getId());
        orderPackDto.setRestaurantName(orderPack.getRestaurant().getName());
        //dto.setTimeLimit(entity.getTimeLimit());
        orderPackDto.setOrderStatusId(orderPack.getOrderStatus().getId());
        orderPackDto.setOrderStatusName(orderPack.getOrderStatus().getName());
        orderPackDto.setStatusChanged(orderPack.getStatusChanged());
        orderPackDto.setCreated(orderPack.getCreated());
        if (orderPack.getOrderMenus() != null)
            orderPackDto.setOrderMenus(orderMenuMapper.convertToDTO(orderPack.getOrderMenus()));
    }

    @Override
    protected void mapDTOToEntity(OrderPackDTO orderPackDto, OrderPack orderPack) {
        orderPack.setId(orderPackDto.getId());
        Optional<User> user = userRepository.findById(orderPackDto.getUserId());
        user.ifPresent(orderPack::setUser);
        orderPack.setComment(orderPackDto.getComment());
        orderPack.setMenuSource(orderPackDto.getMenuSource());
        Optional<Restaurant> restaurant = restaurantRepository.findById(orderPackDto.getRestaurantId());
        restaurant.ifPresent(orderPack::setRestaurant);
        //entity.setTimeLimit(dto.getTimeLimit());
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(orderPackDto.getOrderStatusId());
        orderStatus.ifPresent(orderPack::setOrderStatus);
        /*
        entity.setStatusChanged(dto.getStatusChanged());
        entity.setCreated(dto.getCreated());
        */
        if (orderPackDto.getOrderMenus() != null && orderPackDto.getId() != null)
            orderPack.setOrderMenus(orderMenuMapper.convertToEntity(orderPackDto.getOrderMenus()));
    }
}
