package pl.damian.zamawiam.service.mapper;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GenericMapper<Entity, DTO> {

    protected abstract Entity initEntity();

    protected abstract DTO initDTO();

    protected abstract void mapEntityToDTO(Entity entity, DTO dto);

    protected abstract void mapDTOToEntity(DTO dto, Entity entity);

    public DTO toDTO(Entity entity) {
        if (entity == null)
            return null;

        DTO dto = initDTO();
        mapEntityToDTO(entity, dto);
        return dto;
    }

    public Entity toEntity(DTO dto) {
        if (dto == null)
            return null;

        Entity entity = initEntity();
        mapDTOToEntity(dto, entity);
        return entity;
    }

    public List<DTO> toDTO(List<Entity> entityList) {
        if (entityList == null)
            return Collections.emptyList();

        List<DTO> result = new ArrayList<>();
        for (Entity entity : entityList) {
            result.add(toDTO(entity));
        }
        return result;
    }

    public List<Entity> toEntity(List<DTO> DTOList) {
        if (DTOList == null)
            return Collections.emptyList();

        List<Entity> result = new ArrayList<>();
        for (DTO dto : DTOList) {
            result.add(toEntity(dto));
        }
        return result;
    }
}
