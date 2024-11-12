package inhatc.cse.ksh.suhyeonshop.order.entity;

import inhatc.cse.ksh.suhyeonshop.common.entity.BaseEntity;
import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import inhatc.cse.ksh.suhyeonshop.order.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // 패치타입은 기본 LAZY 타입
    private List<OrderItem> orderItems = new ArrayList<>();

}
