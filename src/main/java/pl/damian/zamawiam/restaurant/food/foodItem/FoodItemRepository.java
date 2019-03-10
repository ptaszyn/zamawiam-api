package pl.damian.zamawiam.restaurant.food.foodItem;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroup;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByFoodGroup(FoodGroup foodGroup);
}
