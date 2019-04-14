package pl.damian.zamawiam.repo.model.order;

import lombok.Data;
import pl.damian.zamawiam.repo.model.restaurant.FoodItem;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderHead orderHead;

    private String ownOrder;

    private BigDecimal amount;

    @ManyToOne
    private OrderMenu orderMenu;

    @ManyToOne
    private FoodItem foodItem;

    @OneToOne
    @JoinColumn(name="parent_id", referencedColumnName="id")
    private OrderItem parentOrderItem;
}
