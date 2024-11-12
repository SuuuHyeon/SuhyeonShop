package inhatc.cse.ksh.suhyeonshop.item.entity;

import inhatc.cse.ksh.suhyeonshop.common.entity.BaseEntity;
import inhatc.cse.ksh.suhyeonshop.item.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table (name = "ABC") // 테이블명 설정가능
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {
    @Id // primary key 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    @Column(name = "item_id")
    private Long id;

    @Column(length = 15, nullable = false) // 길이랑 NotNull 설정하기
    private String itemNm;

    private int price;

    @Column(name = "stock")
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Column
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
}
