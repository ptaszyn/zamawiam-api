package pl.damian.zamawiam.order.orderPack;

import lombok.Data;
import pl.damian.zamawiam.order.orderPack.orderMenu.OrderMenuDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderPackDto {

    private Long id;
    private Long userId;
    private Long restaurantId;
    private String restaurantName;
    private String comment;
    private Long menuSource;
    //private LocalDateTime timeLimit;
    private Long orderStatusId;
    private String orderStatusName;
    private LocalDateTime statusChanged;
    private LocalDateTime created;
    private List<OrderMenuDto> orderMenus;
}
