package pl.damian.zamawiam.restaurant.food.foodGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodGroupService {

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Autowired
    private FoodGroupMapper foodGroupMapper;

    public FoodGroupDto create(FoodGroupDto dto) {
        return saveFoodGroupDto(dto);
    }

    public FoodGroupDto update(FoodGroupDto dto) {
        return saveFoodGroupDto(dto);
    }

    private FoodGroupDto saveFoodGroupDto(FoodGroupDto dto) {
        FoodGroup entity = foodGroupMapper.toEntity(dto);
        FoodGroup entitySaved = foodGroupRepository.save(entity);
        return foodGroupMapper.toDto(entitySaved);
    }
}
