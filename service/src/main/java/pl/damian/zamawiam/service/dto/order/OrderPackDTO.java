package pl.damian.zamawiam.service.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderPackDTO {

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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private LocalDateTime created;

    private List<OrderMenuDTO> orderMenus;
}
