package pl.damian.zamawiam.service.mapper.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.restaurant.FoodGroup;
import pl.damian.zamawiam.repo.model.restaurant.FoodItem;
import pl.damian.zamawiam.repo.repository.restaurant.FoodGroupRepository;
import pl.damian.zamawiam.service.dto.restaurant.FoodItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.Optional;

@Component
public class FoodItemMapper extends GenericMapper<FoodItem, FoodItemDTO> {

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Override
    protected FoodItem initEntity() {
        return new FoodItem();
    }

    @Override
    protected FoodItemDTO initDTO() {
        return new FoodItemDTO();
    }

    @Override
    protected void mapEntityToDTO(FoodItem foodItem, FoodItemDTO foodItemDTO) {
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setEnabled(foodItem.getEnabled());
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setPrice(foodItem.getPrice());
        foodItemDTO.setDescription(foodItem.getDescription());
        foodItemDTO.setFoodGroupId(foodItem.getFoodGroup().getId());
    }

    @Override
    protected void mapDTOToEntity(FoodItemDTO foodItemDTO, FoodItem foodItem) {
        foodItem.setId(foodItemDTO.getId());
        foodItem.setEnabled(foodItemDTO.getEnabled());
        foodItem.setName(foodItemDTO.getName());
        foodItem.setPrice((foodItemDTO.getPrice()));
        foodItem.setDescription(foodItemDTO.getDescription());
        Optional<FoodGroup> foodGroup = foodGroupRepository.findById(foodItemDTO.getFoodGroupId());
        foodGroup.ifPresent(foodItem::setFoodGroup);
    }
}
