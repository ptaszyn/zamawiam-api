package pl.damian.zamawiam.order.orderPack.orderMenu;

import lombok.Data;
import pl.damian.zamawiam.order.orderPack.OrderPack;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItem;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "order_menus")
public class OrderMenu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderPack orderPack;

    private String name;

    private BigDecimal price;

    private String comment;

    @OneToMany(mappedBy="orderMenu")
    private List<OrderItem> orderItems;
}
