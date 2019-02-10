package pl.damian.zamawiam.restaurant.food.foodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.core.Mapper;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroup;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroupRepository;

import java.util.Optional;

@Component
public class FoodItemMapper implements Mapper<FoodItem, FoodItemDto> {

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    public FoodItemDto toDto(FoodItem entity){
        FoodItemDto dto = new FoodItemDto();
        dto.setId(entity.getId());
        dto.setEnabled(entity.getEnabled());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setFoodGroupId(entity.getFoodGroup().getId());
        return dto;
    }

    public FoodItem toEntity(FoodItemDto dto){
        FoodItem entity = new FoodItem();
        entity.setId(dto.getId());
        entity.setEnabled(dto.getEnabled());
        entity.setName(dto.getName());
        entity.setPrice((dto.getPrice()));
        entity.setDescription(dto.getDescription());
        Optional<FoodGroup> foodGroup = foodGroupRepository.findById(dto.getFoodGroupId());
        foodGroup.ifPresent(entity::setFoodGroup);
        return entity;
    }
}
