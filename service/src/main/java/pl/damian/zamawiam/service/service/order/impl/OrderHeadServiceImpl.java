package pl.damian.zamawiam.service.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.repo.model.order.OrderHead;
import pl.damian.zamawiam.repo.model.order.OrderItem;
import pl.damian.zamawiam.repo.model.order.OrderPack;
import pl.damian.zamawiam.repo.repository.order.OrderHeadRepository;
import pl.damian.zamawiam.repo.repository.order.OrderItemRepository;
import pl.damian.zamawiam.repo.repository.order.OrderPackRepository;
import pl.damian.zamawiam.service.dto.order.OrderHeadDTO;
import pl.damian.zamawiam.service.dto.order.OrderItemDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;
import pl.damian.zamawiam.service.service.order.OrderHeadService;
import pl.damian.zamawiam.service.service.security.AuthenticationFacade;
import pl.damian.zamawiam.service.service.security.impl.userDetails.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderHeadServiceImpl implements OrderHeadService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private GenericMapper<OrderHead, OrderHeadDTO> orderHeadMapper;

    @Autowired
    private GenericMapper<OrderItem, OrderItemDTO> orderItemMapper;

    @Override
    public List<OrderHeadDTO> findAllByPackId(Long packId) {
        OrderPack orderPack = orderPackRepository.findById(packId).get();
        return orderHeadMapper.convertToDTO(orderHeadRepository.findByOrderPack(orderPack));
    }

    @Override
    public Optional<OrderHeadDTO> findById(Long packId, Long id) {
        return orderHeadRepository.findById(id).map(orderHeadMapper::convertToDTO);
    }

    @Override
    public OrderHeadDTO create(OrderHeadDTO dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        dto.setUserId(userDetails.getId());
        OrderHeadDTO savedOrderHeadDTO = saveOrderHeadDto(dto);
        savedOrderHeadDTO.setOrderItems(saveOrderItemsDto(dto.getOrderItems(), savedOrderHeadDTO.getId(), null));
        return savedOrderHeadDTO;
    }

    @Override
    public OrderHeadDTO update(OrderHeadDTO dto) {
        dto.setOrderItems(saveOrderItemsDto(dto.getOrderItems(), dto.getId(), null));
        return saveOrderHeadDto(dto);
    }

    private OrderHeadDTO saveOrderHeadDto(OrderHeadDTO dto) {
        OrderHead orderHead = orderHeadMapper.convertToEntity(dto);
        OrderHead orderHeadSaved = orderHeadRepository.save(orderHead);
        return orderHeadMapper.convertToDTO(orderHeadSaved);
    }

    private List<OrderItemDTO> saveOrderItemsDto(List<OrderItemDTO> orderItems, Long orderHeadId, OrderItem parent){
        List<OrderItemDTO> savedOrderItems = new ArrayList<OrderItemDTO>();
        if (orderItems != null && orderItems.size() > 0) {
            savedOrderItems = orderItems.stream().map(orderItemDto -> {
                orderItemDto.setOrderHeadId(orderHeadId);
                OrderItem entity = orderItemMapper.convertToEntity(orderItemDto);
                entity.setParentOrderItem(parent);
                OrderItem entitySaved = orderItemRepository.save(entity);
                OrderItemDTO savedOrderItemDTO = orderItemMapper.convertToDTO(entitySaved);
                savedOrderItemDTO.setSideOrderItems(saveOrderItemsDto(orderItemDto.getSideOrderItems(), orderHeadId, entitySaved));
                return savedOrderItemDTO;
            }).collect(Collectors.toList());
        }
        return savedOrderItems;
    }
}
