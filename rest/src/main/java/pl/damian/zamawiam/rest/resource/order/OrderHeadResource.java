package pl.damian.zamawiam.rest.resource.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.service.dto.order.OrderHeadDTO;
import pl.damian.zamawiam.service.service.order.OrderHeadService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderpacks/{orderPackId}/orderheads")
public class OrderHeadResource {

    @Autowired
    private OrderHeadService orderHeadService;

    @GetMapping
    public List<OrderHeadDTO> getAllByPackId(@PathVariable Long orderPackId, @RequestParam(defaultValue = "false") boolean isOwner) {
        return orderHeadService.findAllByPackId(orderPackId, isOwner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHeadDTO> getOrderHead(@PathVariable Long orderPackId, @PathVariable Long id) {
        return orderHeadService.findById(orderPackId, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderHeadDTO> addOrderHead(@RequestBody OrderHeadDTO dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        OrderHeadDTO orderHeadDto = orderHeadService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderHeadDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderHeadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHeadDTO> putOrderHead(@RequestBody OrderHeadDTO dto){
        OrderHeadDTO orderHeadDto = orderHeadService.update(dto);
        return ResponseEntity.ok(orderHeadDto);
    }
}
