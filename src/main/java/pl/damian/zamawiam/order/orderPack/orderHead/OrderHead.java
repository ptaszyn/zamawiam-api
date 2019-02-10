package pl.damian.zamawiam.order.orderPack.orderHead;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import pl.damian.zamawiam.order.orderPack.orderHead.orderItem.OrderItem;
import pl.damian.zamawiam.order.orderPack.OrderPack;
import pl.damian.zamawiam.order.orderPack.orderHead.payment.Payment;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenu;
import pl.damian.zamawiam.security.user.User;

@Data
@Entity(name="order_heads")
public class OrderHead {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private OrderPack orderPack;

	@ManyToOne
	private User user;

	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private Payment payment;

	private Boolean paid;

	private String comment;

	@OneToMany(mappedBy="orderHead")
	private List<OrderItem> orderItems;

}
