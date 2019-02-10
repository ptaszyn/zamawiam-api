package pl.damian.zamawiam.restaurant.food.foodItem;

import javax.persistence.*;

import lombok.Data;
import pl.damian.zamawiam.restaurant.food.foodGroup.FoodGroup;

import java.math.BigDecimal;

@Data
@Entity(name="food_items")
public class FoodItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private BigDecimal price;

	private String description;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean enabled;

	@Version
	private Integer version;

	@ManyToOne
	private FoodGroup foodGroup;
}
