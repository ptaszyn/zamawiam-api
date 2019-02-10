package pl.damian.zamawiam.restaurant;

public class RestaurantMapper {

	public static RestaurantDto toDto(Restaurant entity) {
		RestaurantDto dto = new RestaurantDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUrl(entity.getUrl());
		dto.setDescription(entity.getDescription());
		dto.setRequirement(entity.getRequirement());
		dto.setContact(entity.getContact());
		dto.setEnabled(entity.getEnabled());
		return dto;
	}

	public static Restaurant toEntity(RestaurantDto dto) {
		Restaurant entity = new Restaurant();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
		entity.setDescription(dto.getDescription());
		entity.setRequirement(dto.getRequirement());
		entity.setContact(dto.getContact());
		entity.setEnabled(dto.getEnabled());
		return entity;
	}
}
