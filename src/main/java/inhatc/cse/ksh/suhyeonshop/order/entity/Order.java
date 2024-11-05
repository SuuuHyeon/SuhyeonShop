package inhatc.cse.ksh.suhyeonshop.order.entity;

import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import inhatc.cse.ksh.suhyeonshop.order.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING) // 문자로 확인
    private OrderStatus orderStatus; // 주문 상태

}
