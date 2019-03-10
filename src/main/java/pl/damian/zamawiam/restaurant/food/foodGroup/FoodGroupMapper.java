package pl.damian.zamawiam.restaurant.food.foodGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItem;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItemMapper;
import pl.damian.zamawiam.restaurant.Restaurant;
import pl.damian.zamawiam.restaurant.RestaurantRepository;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FoodGroupMapper implements Mapper<FoodGroup, FoodGroupDto> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodItemMapper foodItemMapper;

    @Autowired
    private FoodGroupMapper foodGroupMapper;

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodGroupDto toDto(FoodGroup entity) {
        FoodGroupDto dto = new FoodGroupDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIsMain(entity.getIsMain());
        dto.setRestaurantId(entity.getRestaurant().getId());
        if (entity.getFoodItems() != null)
            dto.setFoodItems(entity.getFoodItems().stream().map(foodItemMapper::toDto).collect(Collectors.toList()));
        if (entity.getIsMain()){
            dto.setSideFoodGroups(entity.getSideGroups().stream().map(foodGroupMapper::toDto).collect(Collectors.toList()));
        }
        return dto;
    }

    public FoodGroup toEntity(FoodGroupDto dto) {
        FoodGroup entity = new FoodGroup();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIsMain(dto.getIsMain());
        Optional<Restaurant> restaurant = restaurantRepository.findById(dto.getRestaurantId());
        restaurant.ifPresent(entity::setRestaurant);
        List<FoodItem> foodItems = foodItemRepository.findByFoodGroup(entity);
        entity.setFoodItems(foodItems);
        if(dto.getIsMain()){
            entity.setSideGroups(dto.getSideFoodGroups().stream().map(foodGroupDto -> foodGroupRepository.findById(foodGroupDto.getId()).get()).collect(Collectors.toList()));
        }
        return entity;
    }
}
