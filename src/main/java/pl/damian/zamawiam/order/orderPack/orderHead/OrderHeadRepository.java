package pl.damian.zamawiam.order.orderPack.orderHead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.order.orderPack.OrderPack;

import java.util.List;

@Repository
public interface OrderHeadRepository extends JpaRepository<OrderHead, Long> {

    List<OrderHead> findByOrderPack(OrderPack orderPack);
}
