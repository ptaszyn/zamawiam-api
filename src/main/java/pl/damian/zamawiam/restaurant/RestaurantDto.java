package pl.damian.zamawiam.restaurant;

import lombok.Data;

@Data
public class RestaurantDto {

	private Long id;
	private String name;
	private String url;
	private String contact;
	private String description;
	private String requirement;
	private Boolean enabled;
}
