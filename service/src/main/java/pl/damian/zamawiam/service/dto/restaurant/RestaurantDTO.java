package pl.damian.zamawiam.service.dto.restaurant;

import lombok.Data;

@Data
public class RestaurantDTO {

    private Long id;
    private String name;
    private String url;
    private String contact;
    private String description;
    private String requirement;
    private Boolean enabled;
}
