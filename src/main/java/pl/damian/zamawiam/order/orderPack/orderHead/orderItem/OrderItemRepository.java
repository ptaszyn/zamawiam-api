package pl.damian.zamawiam.order.orderPack.orderHead.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.order.orderPack.orderHead.OrderHead;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderHead(OrderHead orderHead);
}
