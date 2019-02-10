package pl.damian.zamawiam.restaurant;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroupDto;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroupMapper;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroupRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Autowired
    private FoodGroupMapper foodGroupMapper;

    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll().stream().map(RestaurantMapper::toDto).collect(Collectors.toList());
    }

    public Optional<RestaurantDto> findById(Long id) {
        return restaurantRepository.findById(id).map(RestaurantMapper::toDto);
    }

    public RestaurantDto create(RestaurantDto dto) {
        return saveRestaurantDto(dto);
    }

    public RestaurantDto update(RestaurantDto dto) {
        return saveRestaurantDto(dto);
    }

    public List<FoodGroupDto> findMenu(Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return foodGroupRepository.findByRestaurant(restaurant.get()).stream().map(foodGroupMapper::toDto).collect(Collectors.toList());
    }

    private RestaurantDto saveRestaurantDto(RestaurantDto dto) {
        Restaurant entity = RestaurantMapper.toEntity(dto);
        Restaurant entitySaved = restaurantRepository.save(entity);
        return RestaurantMapper.toDto(entitySaved);
    }
}
