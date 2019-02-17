package pl.damian.zamawiam.order.orderPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/orderpacks")
public class OrderPackResource {

    @Autowired
    private OrderPackService orderPackService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderPackDto> get(@PathVariable Long id){
        return orderPackService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderPackDto> post(@RequestBody OrderPackDto dto){
        if(dto.getId()!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        OrderPackDto orderPackDto = orderPackService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderPackDto.getId()).toUri();
        return ResponseEntity.created(location).body(orderPackDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderPackDto> put(@RequestBody OrderPackDto dto){
        OrderPackDto orderPackDto = orderPackService.update(dto);
        return ResponseEntity.ok(orderPackDto);
    }

}
