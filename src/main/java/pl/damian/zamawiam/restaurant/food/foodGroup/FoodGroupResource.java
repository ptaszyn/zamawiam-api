package pl.damian.zamawiam.restaurant.food.foodGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/groups")
public class FoodGroupResource {

    @Autowired
    private FoodGroupService foodGroupService;

    @PostMapping
    public ResponseEntity<FoodGroupDto> postFoodGroup(@RequestBody FoodGroupDto dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        FoodGroupDto foodGroupDto = foodGroupService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(foodGroupDto.getId()).toUri();
        return ResponseEntity.created(location).body(foodGroupDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodGroupDto> putFoodGroup(@RequestBody FoodGroupDto dto) {
        FoodGroupDto foodGroupDto = foodGroupService.update(dto);
        return ResponseEntity.ok(foodGroupDto);
    }
}
