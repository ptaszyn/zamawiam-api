package pl.damian.zamawiam.repo.model.order;

import lombok.Data;
import pl.damian.zamawiam.repo.model.restaurant.Restaurant;
import pl.damian.zamawiam.repo.model.security.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data

@Entity(name="order_packs")
public class OrderPack {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(updatable = false)
    private Restaurant restaurant;

    private String comment;

    private Long menuSource;

    private LocalDateTime timeLimit;

    @ManyToOne
    private OrderStatus orderStatus;

    private LocalDateTime statusChanged;

    @Column(updatable = false)
    private LocalDateTime created;

    @OneToMany(mappedBy="orderPack")
    private List<OrderHead> orderHeads;

    @OneToMany(mappedBy="orderPack")
    private List<OrderMenu> orderMenus;
}
