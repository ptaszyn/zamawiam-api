package pl.damian.zamawiam.repo.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.order.OrderHead;
import pl.damian.zamawiam.repo.model.order.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderHead(OrderHead orderHead);

    List<OrderItem> findByParentOrderItem(OrderItem parent);
}
