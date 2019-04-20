package pl.damian.zamawiam.rest.resource.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.service.dto.order.OrderPackDTO;
import pl.damian.zamawiam.service.service.order.OrderPackService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderpacks")
public class OrderPackResource {

    @Autowired
    private OrderPackService orderPackService;

    @GetMapping
    public List<OrderPackDTO> getAll(@RequestParam(defaultValue = "false") Boolean isOwner){ return orderPackService.findAll(isOwner); }

    @GetMapping("/{id}")
    public ResponseEntity<OrderPackDTO> get(@PathVariable Long id){
        return orderPackService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderPackDTO> post(@RequestBody OrderPackDTO dto){
        if(dto.getId()!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        OrderPackDTO orderPackDto = orderPackService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderPackDto.getId()).toUri();
        return ResponseEntity.created(location).body(orderPackDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderPackDTO> put(@RequestBody OrderPackDTO dto){
        OrderPackDTO orderPackDto = orderPackService.update(dto);
        return ResponseEntity.ok(orderPackDto);
    }
}
