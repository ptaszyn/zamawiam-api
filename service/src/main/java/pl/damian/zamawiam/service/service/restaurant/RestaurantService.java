package pl.damian.zamawiam.service.service.restaurant;

import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;
import pl.damian.zamawiam.service.dto.restaurant.RestaurantDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    public List<RestaurantDTO> findAll();

    public Optional<RestaurantDTO> findById(Long id);

    public RestaurantDTO create(RestaurantDTO dto);

    public RestaurantDTO update(RestaurantDTO dto);

    public List<FoodGroupDTO> findMenu(Long id);
}
