package pl.damian.zamawiam.service.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderMenuDTO {

    private Long id;
    private Long orderPackId;
    private String name;
    private BigDecimal price;
    private String comment;
}
