package pl.damian.zamawiam.order.orderPack.orderHead;

import lombok.Data;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItemDto;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderHeadDto {

    private Long id;
    private Long orderPackId;
    private Long userId;
    private BigDecimal amount;
    private String payment;
    private Boolean paid;
    private String comment;
    private List<OrderItemDto> orderItems;
}
