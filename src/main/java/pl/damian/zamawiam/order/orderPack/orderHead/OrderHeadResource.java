package pl.damian.zamawiam.order.orderPack.orderHead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderpacks/{orderPackId}/orderheads")
public class OrderHeadResource {

    @Autowired
    private OrderHeadService orderHeadService;

    @GetMapping
    public List<OrderHeadDto> getOrderHeads(@PathVariable Long orderPackId) {
        return orderHeadService.findAllByPackId(orderPackId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHeadDto> getOrderHead(@PathVariable Long orderPackId, @PathVariable Long id) {
        return orderHeadService.findById(orderPackId, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderHeadDto> addOrderHead(@RequestBody OrderHeadDto dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        OrderHeadDto orderHeadDto = orderHeadService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderHeadDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderHeadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHeadDto> putOrderHead(@RequestBody OrderHeadDto dto){
        OrderHeadDto orderHeadDto = orderHeadService.update(dto);
        return ResponseEntity.ok(orderHeadDto);
    }

}
