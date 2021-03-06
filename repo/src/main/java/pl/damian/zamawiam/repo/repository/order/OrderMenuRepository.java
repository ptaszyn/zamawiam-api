package pl.damian.zamawiam.repo.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.damian.zamawiam.repo.model.order.OrderMenu;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
