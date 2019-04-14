package pl.damian.zamawiam.service.service.order;

import pl.damian.zamawiam.service.dto.order.OrderItemDTO;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {

    public List<OrderItemDTO> getAllByHeadId(Long headId);

    public Optional<OrderItemDTO> findById(Long id);

    public OrderItemDTO create(OrderItemDTO dto);

    public OrderItemDTO update(OrderItemDTO dto);
}
