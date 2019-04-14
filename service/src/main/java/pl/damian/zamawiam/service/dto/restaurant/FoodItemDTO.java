package pl.damian.zamawiam.service.dto.restaurant;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodItemDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Boolean enabled;
    private Long foodGroupId;
}
