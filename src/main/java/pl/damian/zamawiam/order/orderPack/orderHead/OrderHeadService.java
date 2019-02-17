package pl.damian.zamawiam.order.orderPack.orderHead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.security.auth.AuthenticationFacade;
import pl.damian.zamawiam.security.user.UserDetailsImpl;

import java.util.Optional;

@Service
public class OrderHeadService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderHeadRepository orderHeadRepository;

    @Autowired
    private OrderHeadMapper orderHeadMapper;

    public Optional<OrderHeadDto> findById(Long id){
        return orderHeadRepository.findById(id).map(orderHeadMapper::toDto);
    }

    public OrderHeadDto create(OrderHeadDto dto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        dto.setUserId(userDetails.getId());
        return saveOrderHeadDto(dto);
    }

    public OrderHeadDto update(OrderHeadDto dto) {
        return saveOrderHeadDto(dto);
    }

    private OrderHeadDto saveOrderHeadDto(OrderHeadDto dto) {
        OrderHead orderPack = orderHeadMapper.toEntity(dto);
        OrderHead orderPackSaved = orderHeadRepository.save(orderPack);
        return orderHeadMapper.toDto(orderPackSaved);
    }
}
