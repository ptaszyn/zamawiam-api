package pl.damian.zamawiam.order.orderPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.damian.zamawiam.order.orderPack.orderStatus.OrderStatusRepository;
import pl.damian.zamawiam.security.auth.AuthenticationFacade;
import pl.damian.zamawiam.security.user.UserDetailsImpl;

@Service
public class OrderPackService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderPackMapper orderPackMapper;

    public OrderPackDto create(OrderPackDto dto){
        UserDetailsImpl userDetails = (UserDetailsImpl)authenticationFacade.getAuthentication().getPrincipal();
        dto.setUserId(userDetails.getId());
        dto.setOrderStatusId(orderStatusRepository.findFirstByOrderBySequenceAsc().getId());
        return saveOrderPackDto(dto);
    }

    public OrderPackDto update(OrderPackDto dto){
        return saveOrderPackDto(dto);
    }

    private OrderPackDto saveOrderPackDto(OrderPackDto dto) {
        OrderPack orderPack = orderPackMapper.toEntity(dto);
        OrderPack orderPackSaved = orderPackRepository.save(orderPack);
        return orderPackMapper.toDto(orderPackSaved);
    }
}
