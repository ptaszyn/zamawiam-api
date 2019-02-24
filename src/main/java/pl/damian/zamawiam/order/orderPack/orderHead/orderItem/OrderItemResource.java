package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderpacks/{orderPackId}/orderheads/{orderHeadId}/orderItems")
public class OrderItemResource {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDto> getOrderItems(@PathVariable Long orderPackId, @PathVariable Long orderHeadId){
        return orderItemService.getAllByHeadId(orderHeadId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItem(@PathVariable Long id){
        return orderItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> addOrderHead(@RequestBody OrderItemDto dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        OrderItemDto orderItemDto = orderItemService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderItemDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderItemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> putOrderHead(@RequestBody OrderItemDto dto){
        OrderItemDto orderHeadDto = orderItemService.update(dto);
        return ResponseEntity.ok(orderHeadDto);
    }
}
