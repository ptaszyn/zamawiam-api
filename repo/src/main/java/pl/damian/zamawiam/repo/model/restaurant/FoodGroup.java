package pl.damian.zamawiam.repo.model.restaurant;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(updatable = false)
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
