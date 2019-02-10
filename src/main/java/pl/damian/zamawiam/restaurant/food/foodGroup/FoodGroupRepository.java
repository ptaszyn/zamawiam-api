package pl.damian.zamawiam.restaurant.food.foodGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.zamawiam.restaurant.Restaurant;

import java.util.List;

public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long> {

    List<FoodGroup> findByRestaurant(Restaurant restaurant);
}
