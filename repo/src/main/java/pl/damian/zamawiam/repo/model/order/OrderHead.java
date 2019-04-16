package pl.damian.zamawiam.repo.model.order;

import lombok.Data;
import pl.damian.zamawiam.repo.model.security.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name="order_heads")
public class OrderHead {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(updatable = false)
    private OrderPack orderPack;

    @ManyToOne
    @JoinColumn(updatable = false)
    private User user;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    private Boolean paid;

    private String comment;

    private String message;

    @OneToMany(mappedBy="orderHead")
    private List<OrderItem> orderItems;
}
