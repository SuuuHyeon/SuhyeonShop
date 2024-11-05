package inhatc.cse.ksh.suhyeonshop.cart.repository;

import inhatc.cse.ksh.suhyeonshop.cart.entity.Cart;
import inhatc.cse.ksh.suhyeonshop.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
