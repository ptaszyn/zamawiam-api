package pl.damian.zamawiam.service.service.order;

import pl.damian.zamawiam.service.dto.PatchDTO;
import pl.damian.zamawiam.service.dto.order.OrderPackDTO;

import java.util.List;
import java.util.Optional;

public interface OrderPackService {

    public List<OrderPackDTO> findAll(boolean isOwner);

    public Optional<OrderPackDTO> findById(Long id);

    public OrderPackDTO create(OrderPackDTO dto);

    public OrderPackDTO update(OrderPackDTO dto);

    public OrderPackDTO patch(Long id, List<PatchDTO> dto);
}
