package pl.damian.zamawiam.restaurant.food.foodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodItemMapper foodItemMapper;

    public FoodItemDto create(FoodItemDto dto){
        return saveFoodItemDto(dto);
    }

    public FoodItemDto update(FoodItemDto dto){
        return saveFoodItemDto(dto);
    }

    private FoodItemDto saveFoodItemDto(FoodItemDto dto) {
        FoodItem foodItem = foodItemMapper.toEntity(dto);
        FoodItem foodItemSaved = foodItemRepository.save(foodItem);
        return foodItemMapper.toDto(foodItemSaved);
    }
}
