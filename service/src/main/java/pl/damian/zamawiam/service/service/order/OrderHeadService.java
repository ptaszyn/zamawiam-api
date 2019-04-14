package pl.damian.zamawiam.service.service.order;

import pl.damian.zamawiam.service.dto.order.OrderHeadDTO;

import java.util.List;
import java.util.Optional;

public interface OrderHeadService {

    public List<OrderHeadDTO> findAllByPackId(Long packId);

    public Optional<OrderHeadDTO> findById(Long packId, Long id);

    public OrderHeadDTO create(OrderHeadDTO dto);

    public OrderHeadDTO update(OrderHeadDTO dto);
}
