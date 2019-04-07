package pl.damian.zamawiam.order.orderPack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.security.user.User;

import java.util.List;

@Repository
public interface OrderPackRepository extends JpaRepository<OrderPack, Long> {

    List<OrderPack> findByUser(User user);
}
