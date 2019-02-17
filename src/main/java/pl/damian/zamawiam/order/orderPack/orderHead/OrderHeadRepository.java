package pl.damian.zamawiam.order.orderPack.orderHead;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHeadRepository extends JpaRepository<OrderHead, Long> {

}
