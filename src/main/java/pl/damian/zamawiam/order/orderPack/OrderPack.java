package pl.damian.zamawiam.order.orderPack;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHead;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenu;
import pl.damian.zamawiam.order.orderPack.orderStatus.OrderStatus;
import pl.damian.zamawiam.restaurant.Restaurant;
import pl.damian.zamawiam.security.user.User;

@Data
@Entity(name="order_packs")
public class OrderPack {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Restaurant restaurant;

	private String comment;

	private Long menuSource;

	@ManyToOne
	private OrderStatus orderStatus;

	private LocalDateTime statusChanged;

	private LocalDateTime created;

	@OneToMany(mappedBy="orderPack")
	private List<OrderHead> orderHeads;

	@OneToMany(mappedBy="orderPack")
	private List<OrderMenu> orderMenus;
}
