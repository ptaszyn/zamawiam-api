package pl.damian.zamawiam.service.dto.restaurant;

import lombok.Data;

import java.util.List;

@Data
public class FoodGroupDTO {

    private Long id;
    private String name;
    private Long restaurantId;
    private Boolean isMain;
    private List<FoodItemDTO> foodItems;
    private List<FoodGroupDTO> sideFoodGroups;
}
