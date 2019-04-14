package pl.damian.zamawiam.repo.model.order;

import lombok.Data;

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
