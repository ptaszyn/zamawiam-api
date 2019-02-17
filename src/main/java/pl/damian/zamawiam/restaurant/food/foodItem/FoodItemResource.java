package pl.damian.zamawiam.restaurant.food.foodItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/groups/{groupId}/items")
public class FoodItemResource {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItemDto> postFoodItem(@RequestBody FoodItemDto dto){
        if(dto.getId()!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        FoodItemDto foodItemDto = foodItemService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(foodItemDto.getId()).toUri();
        return ResponseEntity.created(location).body(foodItemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemDto> putFoodItem(@RequestBody FoodItemDto dto){
        if(dto.getId()==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        FoodItemDto foodItemDto = foodItemService.update(dto);
        return ResponseEntity.ok(foodItemDto);
    }
}
