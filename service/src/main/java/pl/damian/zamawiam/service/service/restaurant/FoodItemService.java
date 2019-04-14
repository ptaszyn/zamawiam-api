package pl.damian.zamawiam.service.service.restaurant;

import pl.damian.zamawiam.service.dto.restaurant.FoodItemDTO;

public interface FoodItemService {

    public FoodItemDTO create(FoodItemDTO dto);

    public FoodItemDTO update(FoodItemDTO dto);
}
