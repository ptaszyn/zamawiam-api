package pl.damian.zamawiam.service.dto.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderHeadDTO {

    /* on create */
    private Long id;
    private Long orderPackId;
    private Long userId;
    private String userName;

    /* during order */
    private String payment;
    private String comment;
    private List<OrderItemDTO> orderItems;

    /* on pay */
    private BigDecimal amount;
    private Boolean paid;
    private String message;
}
