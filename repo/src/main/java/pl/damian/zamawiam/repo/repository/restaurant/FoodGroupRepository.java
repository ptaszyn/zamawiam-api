package pl.damian.zamawiam.repo.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.restaurant.FoodGroup;
import pl.damian.zamawiam.repo.model.restaurant.Restaurant;

import java.util.List;

@Repository
public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long> {

    List<FoodGroup> findByRestaurant(Restaurant restaurant);
}
