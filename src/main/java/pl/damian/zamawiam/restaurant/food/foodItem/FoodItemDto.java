package pl.damian.zamawiam.restaurant.food.foodItem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodItemDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Boolean enabled;
    private Long foodGroupId;
}
