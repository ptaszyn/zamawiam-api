package pl.damian.zamawiam.service.service.restaurant.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.restaurant.FoodItem;
import pl.damian.zamawiam.repo.repository.restaurant.FoodItemRepository;
import pl.damian.zamawiam.service.dto.restaurant.FoodItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.restaurant.FoodItemService;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private GenericMapper<FoodItem, FoodItemDTO> foodItemMapper;

    @Override
    public FoodItemDTO create(FoodItemDTO dto) {
        FoodItem foodItem = foodItemMapper.toEntity(dto);
        FoodItem foodItemSaved = foodItemRepository.save(foodItem);
        return foodItemMapper.toDTO(foodItemSaved);
    }

    @Override
    public FoodItemDTO update(FoodItemDTO dto) {
        FoodItem entity = foodItemMapper.toEntity(dto);
        foodItemRepository.findById(entity.getId()).ifPresent(foodItem -> entity.setVersion(foodItem.getVersion()));
        FoodItem foodItemSaved = foodItemRepository.save(entity);
        return foodItemMapper.toDTO(foodItemSaved);
    }
}
