package pl.damian.zamawiam.repo.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.restaurant.FoodGroup;
import pl.damian.zamawiam.repo.model.restaurant.FoodItem;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByFoodGroup(FoodGroup foodGroup);
}
