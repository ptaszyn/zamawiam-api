package pl.damian.zamawiam.repo.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.order.OrderHead;
import pl.damian.zamawiam.repo.model.order.OrderPack;

import java.util.List;

@Repository
public interface OrderHeadRepository extends JpaRepository<OrderHead, Long> {

    List<OrderHead> findByOrderPack(OrderPack orderPack);
}
