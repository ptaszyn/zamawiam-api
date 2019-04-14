package pl.damian.zamawiam.service.dto.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderHeadDTO {

    private Long id;
    private Long orderPackId;
    private Long userId;
    private BigDecimal amount;
    private String payment;
    private Boolean paid;
    private String comment;
    private List<OrderItemDTO> orderItems;
}
