package pl.damian.zamawiam.order.orderPack.orderMenu;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderMenuDto {

    private Long id;
    private Long orderPackId;
    private String name;
    private BigDecimal price;
    private String comment;
}
