package pl.damian.zamawiam.rest.resource.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.service.dto.order.OrderItemDTO;
import pl.damian.zamawiam.service.service.order.OrderItemService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderpacks/{orderPackId}/orderheads/{orderHeadId}/sideOrderItems")
public class OrderItemResource {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> getOrderItems(@PathVariable Long orderPackId, @PathVariable Long orderHeadId){
        return orderItemService.getAllByHeadId(orderHeadId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItem(@PathVariable Long id){
        return orderItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> addOrderItem(@RequestBody OrderItemDTO dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        OrderItemDTO orderItemDto = orderItemService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderItemDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderItemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> putOrderItem(@RequestBody OrderItemDTO dto){
        OrderItemDTO orderHeadDto = orderItemService.update(dto);
        return ResponseEntity.ok(orderHeadDto);
    }
}
