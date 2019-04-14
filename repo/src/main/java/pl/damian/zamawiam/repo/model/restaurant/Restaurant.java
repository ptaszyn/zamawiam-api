package pl.damian.zamawiam.repo.model.restaurant;

import lombok.Data;
import pl.damian.zamawiam.repo.model.order.OrderPack;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    private String contact;

    private String description;

    private String requirement;

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodGroup> foodGroups;

    @OneToMany(mappedBy = "restaurant")
    private List<OrderPack> orderPacks;

}