package pl.damian.zamawiam.repo.model.restaurant;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name="food_items")
public class FoodItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
