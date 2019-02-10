package pl.damian.zamawiam.order.orderPack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPackRepository extends JpaRepository<OrderPack, Long> {

}
