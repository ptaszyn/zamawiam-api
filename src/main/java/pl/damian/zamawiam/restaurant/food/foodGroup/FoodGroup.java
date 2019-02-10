package pl.damian.zamawiam.restaurant.food.foodGroup;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItem;
import pl.damian.zamawiam.restaurant.Restaurant;

@Data
@Entity(name = "food_groups")
public class FoodGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean isMain;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodGroup")
    private List<FoodItem> foodItems;

    @JoinTable(name = "group_groups",
            joinColumns = {@JoinColumn(name = "main_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "side_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private List<FoodGroup> sideGroups;

    @ManyToMany(mappedBy = "sideGroups")
    private List<FoodGroup> mainGroups;
}
