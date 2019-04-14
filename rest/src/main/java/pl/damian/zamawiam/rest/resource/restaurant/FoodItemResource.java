package pl.damian.zamawiam.rest.resource.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.service.dto.restaurant.FoodItemDTO;
import pl.damian.zamawiam.service.service.restaurant.FoodItemService;

import java.net.URI;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/groups/{groupId}/items")
public class FoodItemResource {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItemDTO> postFoodItem(@RequestBody FoodItemDTO dto){
        if(dto.getId()!=null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        FoodItemDTO foodItemDto = foodItemService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(foodItemDto.getId()).toUri();
        return ResponseEntity.created(location).body(foodItemDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemDTO> putFoodItem(@RequestBody FoodItemDTO dto){
        if(dto.getId()==null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        FoodItemDTO foodItemDto = foodItemService.update(dto);
        return ResponseEntity.ok(foodItemDto);
    }
}
