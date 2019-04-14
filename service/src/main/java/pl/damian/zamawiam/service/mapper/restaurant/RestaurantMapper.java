package pl.damian.zamawiam.service.mapper.restaurant;

import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.restaurant.Restaurant;
import pl.damian.zamawiam.service.dto.restaurant.RestaurantDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

@Component
public class RestaurantMapper extends GenericMapper<Restaurant, RestaurantDTO> {

    @Override
    protected Restaurant initEntity() {
        return new Restaurant();
    }

    @Override
    protected RestaurantDTO initDTO() {
        return new RestaurantDTO();
    }

    @Override
    protected void mapEntitytoDTO(Restaurant restaurant, RestaurantDTO restaurantDTO) {
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setUrl(restaurant.getUrl());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setRequirement(restaurant.getRequirement());
        restaurantDTO.setContact(restaurant.getContact());
        restaurantDTO.setEnabled(restaurant.getEnabled());
    }

    @Override
    protected void mapDTOToEntity(RestaurantDTO restaurantDTO, Restaurant restaurant) {
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setUrl(restaurantDTO.getUrl());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setRequirement(restaurantDTO.getRequirement());
        restaurant.setContact(restaurantDTO.getContact());
        restaurant.setEnabled(restaurantDTO.getEnabled());
    }
}
