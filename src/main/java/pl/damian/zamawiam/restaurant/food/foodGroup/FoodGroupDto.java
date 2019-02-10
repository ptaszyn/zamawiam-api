package pl.damian.zamawiam.restaurant.food.foodGroup;

import lombok.Data;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItemDto;

import java.util.List;

@Data
public class FoodGroupDto {

    private Long id;
    private String name;
    private Long restaurantId;
    private Boolean isMain;
    private List<FoodItemDto> foodItems;
}
