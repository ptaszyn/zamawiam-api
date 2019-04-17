package pl.damian.zamawiam.service.dto.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderItemDTO {

    private Long id;
    private Long orderHeadId;
    private String ownOrder;
    private BigDecimal amount;
    private Long orderMenuId;
    private Long foodItemId;
    private List<OrderItemDTO> sideOrderItems;
}
