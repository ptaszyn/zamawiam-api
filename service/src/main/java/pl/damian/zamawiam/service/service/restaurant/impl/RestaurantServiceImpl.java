package pl.damian.zamawiam.service.service.restaurant.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.restaurant.FoodGroup;
import pl.damian.zamawiam.repo.model.restaurant.Restaurant;
import pl.damian.zamawiam.repo.repository.restaurant.FoodGroupRepository;
import pl.damian.zamawiam.repo.repository.restaurant.RestaurantRepository;
import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;
import pl.damian.zamawiam.service.dto.restaurant.RestaurantDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.restaurant.RestaurantService;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Autowired
    private GenericMapper<Restaurant, RestaurantDTO> restaurantMapper;

    @Autowired
    private GenericMapper<FoodGroup, FoodGroupDTO> foodGroupMapper;

    @Override
    public List<RestaurantDTO> findAll() {
        return restaurantMapper.toDTO(restaurantRepository.findAll());
    }

    @Override
    public Optional<RestaurantDTO> findById(Long id) {
        return restaurantRepository.findById(id).map(restaurantMapper::toDTO);
    }

    @Override
    public RestaurantDTO create(RestaurantDTO dto) {
        Restaurant entity = restaurantMapper.toEntity(dto);
        Restaurant entitySaved = restaurantRepository.save(entity);
        return restaurantMapper.toDTO(entitySaved);
    }

    @Override
    public RestaurantDTO update(RestaurantDTO dto) {
        Restaurant entity = restaurantMapper.toEntity(dto);
        restaurantRepository.findById(entity.getId()).ifPresent(restaurant -> entity.setVersion(restaurant.getVersion()));
        Restaurant entitySaved = restaurantRepository.save(entity);
        return restaurantMapper.toDTO(entitySaved);
    }

    @Override
    public List<FoodGroupDTO> findMenu(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return foodGroupMapper.toDTO(foodGroupRepository.findByRestaurant(restaurant.get()));
    }
}
