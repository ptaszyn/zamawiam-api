package pl.damian.zamawiam.service.service.restaurant.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.restaurant.FoodGroup;
import pl.damian.zamawiam.repo.repository.restaurant.FoodGroupRepository;
import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.restaurant.FoodGroupService;

@Service
public class FoodGroupServiceImpl implements FoodGroupService {

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    @Autowired
    private GenericMapper<FoodGroup, FoodGroupDTO> foodGroupMapper;

    @Override
    public FoodGroupDTO create(FoodGroupDTO dto) {
        return saveFoodGroupDto(dto);
    }

    @Override
    public FoodGroupDTO update(FoodGroupDTO dto) {
        return saveFoodGroupDto(dto);
    }

    private FoodGroupDTO saveFoodGroupDto(FoodGroupDTO dto) {
        FoodGroup entity = foodGroupMapper.toEntity(dto);
        FoodGroup entitySaved = foodGroupRepository.save(entity);
        return foodGroupMapper.toDTO(entitySaved);
    }
}
