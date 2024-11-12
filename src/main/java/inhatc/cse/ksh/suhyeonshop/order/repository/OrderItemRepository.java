package inhatc.cse.ksh.suhyeonshop.order.repository;

import inhatc.cse.ksh.suhyeonshop.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
