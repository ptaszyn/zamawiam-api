package pl.damian.zamawiam.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.damian.zamawiam.repo.model.order.OrderMenu;
import pl.damian.zamawiam.repo.model.order.OrderPack;
import pl.damian.zamawiam.repo.repository.order.OrderPackRepository;
import pl.damian.zamawiam.service.dto.order.OrderMenuDTO;
import pl.damian.zamawiam.service.mapper.GenericMapper;

import java.util.Optional;

@Component
public class OrderMenuMapper extends GenericMapper<OrderMenu, OrderMenuDTO> {

    @Autowired
    private OrderPackRepository orderPackRepository;

    @Override
    protected OrderMenu initEntity() {
        return new OrderMenu();
    }

    @Override
    protected OrderMenuDTO initDTO() {
        return new OrderMenuDTO();
    }

    @Override
    protected void mapEntityToDTO(OrderMenu orderMenu, OrderMenuDTO orderMenuDto) {
        orderMenuDto.setId(orderMenu.getId());
        orderMenuDto.setName(orderMenu.getName());
        orderMenuDto.setComment(orderMenu.getComment());
        orderMenuDto.setPrice(orderMenu.getPrice());
        orderMenuDto.setOrderPackId(orderMenu.getOrderPack().getId());
    }

    @Override
    protected void mapDTOToEntity(OrderMenuDTO orderMenuDto, OrderMenu orderMenu) {
        orderMenu.setId(orderMenuDto.getId());
        orderMenu.setName(orderMenuDto.getName());
        orderMenu.setComment(orderMenuDto.getComment());
        orderMenu.setPrice(orderMenuDto.getPrice());
        Optional<OrderPack> orderPack = orderPackRepository.findById(orderMenuDto.getOrderPackId());
        orderPack.ifPresent(orderMenu::setOrderPack);
    }
}
