package inhatc.cse.ksh.suhyeonshop.cart.entity;

import inhatc.cse.ksh.suhyeonshop.common.entity.BaseEntity;
import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // LAZY로 설정해서 나중에 로딩하도록 설정. 성능에 문제가 없도록 함.
    @JoinColumn(name = "member_id") // 조인할 엔티티의 컬럼네임. member_id로 설정했던 걸 적어줌
    private Member member; // 멤버와 매핑
}
