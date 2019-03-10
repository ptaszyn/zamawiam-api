package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import javax.persistence.*;

import lombok.Data;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHead;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenu;
import pl.damian.zamawiam.restaurant.food.foodItem.FoodItem;

import java.math.BigDecimal;

@Data
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderHead orderHead;

    @ManyToOne
    private OrderMenu orderMenu;

    @ManyToOne
    private FoodItem foodItem;

    private String ownOrder;

    private BigDecimal amount;

    @OneToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name="parent_id", referencedColumnName="id", nullable = true)
    private OrderItem parentOrderItem;
}
