package pl.damian.zamawiam.restaurant.food.foodGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItemMapper;
import pl.damian.zamawiam.restaurant.Restaurant;
import pl.damian.zamawiam.restaurant.RestaurantRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FoodGroupMapper implements Mapper<FoodGroup, FoodGroupDto> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodItemMapper foodItemMapper;

    public FoodGroupDto toDto(FoodGroup entity) {
        FoodGroupDto dto = new FoodGroupDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIsMain(entity.getIsMain());
        dto.setRestaurantId(entity.getRestaurant().getId());
        if (entity.getFoodItems() != null)
            dto.setFoodItems(entity.getFoodItems().stream().map(foodItemMapper::toDto).collect(Collectors.toList()));
        return dto;
    }

    public FoodGroup toEntity(FoodGroupDto dto) {
        FoodGroup entity = new FoodGroup();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIsMain(dto.getIsMain());
        Optional<Restaurant> restaurant = restaurantRepository.findById(dto.getRestaurantId());
        restaurant.ifPresent(entity::setRestaurant);
        return entity;
    }
}
