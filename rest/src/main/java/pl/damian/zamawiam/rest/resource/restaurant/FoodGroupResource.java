package pl.damian.zamawiam.rest.resource.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.damian.zamawiam.service.dto.restaurant.FoodGroupDTO;
import pl.damian.zamawiam.service.service.restaurant.FoodGroupService;

import java.net.URI;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/groups")
public class FoodGroupResource {

    @Autowired
    private FoodGroupService foodGroupService;

    @PostMapping
    public ResponseEntity<FoodGroupDTO> postFoodGroup(@RequestBody FoodGroupDTO dto) {
        if (dto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        FoodGroupDTO foodGroupDto = foodGroupService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(foodGroupDto.getId()).toUri();
        return ResponseEntity.created(location).body(foodGroupDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodGroupDTO> putFoodGroup(@RequestBody FoodGroupDTO dto) {
        FoodGroupDTO foodGroupDto = foodGroupService.update(dto);
        return ResponseEntity.ok(foodGroupDto);
    }
}
