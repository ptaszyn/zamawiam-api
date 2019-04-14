package pl.damian.zamawiam.service.mapper;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GenericMapper<Entity, DTO> {

    protected abstract Entity initEntity();

    protected abstract DTO initDTO();

    protected abstract void mapEntitytoDTO(Entity entity, DTO dto);

    protected abstract void mapDTOToEntity(DTO dto, Entity entity);

    public DTO convertToDTO(Entity entity) {
        if (entity == null)
            return null;

        DTO dto = initDTO();
        mapEntitytoDTO(entity, dto);
        return dto;
    }

    public Entity convertToEntity(DTO dto) {
        if (dto == null)
            return null;

        Entity entity = initEntity();
        mapDTOToEntity(dto, entity);
        return entity;
    }

    public List<DTO> convertToDTO(List<Entity> entityList) {
        if (entityList == null)
            return Collections.emptyList();

        List<DTO> result = new ArrayList<>();
        for (Entity entity : entityList) {
            result.add(convertToDTO(entity));
        }
        return result;
    }

    public List<Entity> convertToEntity(List<DTO> DTOList) {
        if (DTOList == null)
            return Collections.emptyList();

        List<Entity> result = new ArrayList<>();
        for (DTO dto : DTOList) {
            result.add(convertToEntity(dto));
        }
        return result;
    }
}
