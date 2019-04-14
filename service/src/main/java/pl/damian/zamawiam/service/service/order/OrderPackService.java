package pl.damian.zamawiam.service.service.order;

import pl.damian.zamawiam.service.dto.order.OrderPackDTO;

import java.util.List;
import java.util.Optional;

public interface OrderPackService {

    public List<OrderPackDTO> findAllByUser();

    public Optional<OrderPackDTO> findById(Long id);

    public OrderPackDTO create(OrderPackDTO dto);

    public OrderPackDTO update(OrderPackDTO dto);
}
