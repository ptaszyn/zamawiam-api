package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemDto {

    private Long id;
    private Long orderHeadId;
    private String ownOrder;
    private BigDecimal amount;
    private Long orderMenuId;
    private Long foodItemId;
    private List<OrderItemDto> orderItems;
}
