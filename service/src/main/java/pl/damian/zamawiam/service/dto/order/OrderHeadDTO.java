package pl.damian.zamawiam.service.dto.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderHeadDTO {

    private Long id;
    private Long orderPackId;
    private Long userId;
    private String userName;
    private String payment;
    private String comment;
    private BigDecimal amount;
    private Boolean paid;
    private String message;
    private List<OrderItemDTO> orderItems;
}
