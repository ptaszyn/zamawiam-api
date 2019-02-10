package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long id;
    private Long orderHeadId;
    private Long orderMenuId;
    private Long foodItemId;
    private String ownOrder;
    private BigDecimal amount;
    private String comment;
}
