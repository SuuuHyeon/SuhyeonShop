package inhatc.cse.ksh.suhyeonshop.order.repository;

import inhatc.cse.ksh.suhyeonshop.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
