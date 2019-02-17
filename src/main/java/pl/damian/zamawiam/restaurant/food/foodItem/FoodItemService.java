package pl.damian.zamawiam.restaurant.food.foodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodItemMapper foodItemMapper;

    public FoodItemDto create(FoodItemDto dto) {
        FoodItem foodItem = foodItemMapper.toEntity(dto);
        FoodItem foodItemSaved = foodItemRepository.save(foodItem);
        return foodItemMapper.toDto(foodItemSaved);
    }

    public FoodItemDto update(FoodItemDto dto) {
        FoodItem entity = foodItemMapper.toEntity(dto);
        foodItemRepository.findById(entity.getId()).ifPresent(foodItem -> entity.setVersion(foodItem.getVersion()));
        FoodItem foodItemSaved = foodItemRepository.save(entity);
        return foodItemMapper.toDto(foodItemSaved);
    }
}
