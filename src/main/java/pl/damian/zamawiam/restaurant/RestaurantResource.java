package pl.damian.zamawiam.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroupDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantResource {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long id) {
        return restaurantService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> postRestaurant(@RequestBody RestaurantDto dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        RestaurantDto restaurantDto = restaurantService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurantDto.getId()).toUri();
        return ResponseEntity.created(location).body(restaurantDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> putRestaurant(@RequestBody RestaurantDto dto) {
        RestaurantDto restaurantDto = restaurantService.update(dto);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/{id}/menu")
    public List<FoodGroupDto> getRestaurantMenu(@PathVariable Long id) {
        return restaurantService.findMenu(id);
    }

}
