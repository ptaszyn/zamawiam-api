package pl.damian.zamawiam.service.service.restaurant;

import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;

public interface FoodGroupService {

    public FoodGroupDTO create(FoodGroupDTO dto);

    public FoodGroupDTO update(FoodGroupDTO dto);
}
