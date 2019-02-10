package pl.damian.zamawiam.order.orderPack.orderHead;

import java.math.BigDecimal;

public class OrderHeadDto {

    private Long id;
    private Long orderPackId;
    private Long userId;
    private BigDecimal amount;
    private String payment;
    private Boolean paid;
    private String comment;
}
