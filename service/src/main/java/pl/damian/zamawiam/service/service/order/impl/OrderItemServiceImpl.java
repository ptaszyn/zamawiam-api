package pl.damian.zamawiam.service.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.order.OrderHead;
import pl.damian.zamawiam.repo.model.order.OrderItem;
import pl.damian.zamawiam.repo.repository.order.OrderHeadRepository;
import pl.damian.zamawiam.repo.repository.order.OrderItemRepository;
import pl.damian.zamawiam.service.dto.order.OrderItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.order.OrderItemService;
import pl.damian.zamawiam.service.service.security.AuthenticationFacade;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    @Autowired
    private GenericMapper<OrderItem, OrderItemDTO> orderItemMapper;

    @Override
    public List<OrderItemDTO> getAllByHeadId(Long headId) {
        OrderHead orderHead = orderHeadRepository.findById(headId).get();
        return orderItemMapper.convertToDTO(orderItemRepository.findByOrderHead(orderHead));
    }

    @Override
    public Optional<OrderItemDTO> findById(Long id) {
        return orderItemRepository.findById(id).map(orderItemMapper::convertToDTO);
    }

    @Override
    public OrderItemDTO create(OrderItemDTO dto) {
        return saveOrderItemDto(dto);
    }

    @Override
    public OrderItemDTO update(OrderItemDTO dto) {
        return saveOrderItemDto(dto);
    }

    private OrderItemDTO saveOrderItemDto(OrderItemDTO dto) {
        OrderItem orderItem = orderItemMapper.convertToEntity(dto);
        OrderItem orderItemSaved = orderItemRepository.save(orderItem);
        return orderItemMapper.convertToDTO(orderItemSaved);
    }
}
