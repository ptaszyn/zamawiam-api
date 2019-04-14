package pl.damian.zamawiam.rest.resource.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;
import pl.damian.zamawiam.service.dto.restaurant.RestaurantDTO;
import pl.damian.zamawiam.service.service.restaurant.RestaurantService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantResource {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        return restaurantService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> postRestaurant(@RequestBody RestaurantDTO dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        RestaurantDTO restaurantDto = restaurantService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurantDto.getId()).toUri();
        return ResponseEntity.created(location).body(restaurantDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> putRestaurant(@RequestBody RestaurantDTO dto) {
        RestaurantDTO restaurantDto = restaurantService.update(dto);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/{id}/menu")
    public List<FoodGroupDTO> getRestaurantMenu(@PathVariable Long id) {
        return restaurantService.findMenu(id);
    }
}
