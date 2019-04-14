package pl.damian.zamawiam.service.mapper.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.restaurant.FoodGroup;
import pl.damian.zamawiam.repo.model.restaurant.FoodItem;
import pl.damian.zamawiam.repo.model.restaurant.Restaurant;
import pl.damian.zamawiam.repo.repository.restaurant.FoodGroupRepository;
import pl.damian.zamawiam.repo.repository.restaurant.FoodItemRepository;
import pl.damian.zamawiam.repo.repository.restaurant.RestaurantRepository;
import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;
import pl.damian.zamawiam.service.dto.restaurant.FoodItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FoodGroupMapper extends GenericMapper<FoodGroup, FoodGroupDTO> {

    @Autowired
    private GenericMapper<FoodItem, FoodItemDTO> foodItemMapper;

    @Autowired
    private GenericMapper<FoodGroup, FoodGroupDTO> foodGroupMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Override
    protected FoodGroup initEntity() {
        return new FoodGroup();
    }

    @Override
    protected FoodGroupDTO initDTO() {
        return new FoodGroupDTO();
    }

    @Override
    protected void mapEntitytoDTO(FoodGroup foodGroup, FoodGroupDTO foodGroupDTO) {
        foodGroupDTO.setId(foodGroup.getId());
        foodGroupDTO.setName(foodGroup.getName());
        foodGroupDTO.setIsMain(foodGroup.getIsMain());
        foodGroupDTO.setRestaurantId(foodGroup.getRestaurant().getId());
        if (foodGroup.getFoodItems() != null)
            foodGroupDTO.setFoodItems(foodItemMapper.convertToDTO(foodGroup.getFoodItems()));
        if (foodGroup.getIsMain()){
            foodGroupDTO.setSideFoodGroups(foodGroupMapper.convertToDTO(foodGroup.getSideGroups()));
        }
    }

    @Override
    protected void mapDTOToEntity(FoodGroupDTO foodGroupDTO, FoodGroup foodGroup) {
        foodGroup.setId(foodGroupDTO.getId());
        foodGroup.setName(foodGroupDTO.getName());
        foodGroup.setIsMain(foodGroupDTO.getIsMain());
        Optional<Restaurant> restaurant = restaurantRepository.findById(foodGroupDTO.getRestaurantId());
        restaurant.ifPresent(foodGroup::setRestaurant);
        List<FoodItem> foodItems = foodItemRepository.findByFoodGroup(foodGroup);
        foodGroup.setFoodItems(foodItems);
        if(foodGroupDTO.getIsMain()){
            foodGroup.setSideGroups(foodGroupDTO.getSideFoodGroups().stream()
                    .map(foodGroupDto -> foodGroupRepository.findById(foodGroupDto.getId()).get())
                    .collect(Collectors.toList()));
        }
    }
}
