package inhatc.cse.ksh.suhyeonshop.item.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.InsertClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.cse.ksh.suhyeonshop.item.constant.ItemSellStatus;
import inhatc.cse.ksh.suhyeonshop.item.entity.Item;
import inhatc.cse.ksh.suhyeonshop.item.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static inhatc.cse.ksh.suhyeonshop.item.entity.QItem.item;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
//    @Autowired
    private EntityManager em;

    @Test
    void findByItemNm() {
    }

    @Test
    void findByItemNmLikeTest() {
        List<Item> itemList = itemRepository.findByItemNmLike("%1%");
        itemList.forEach(item -> System.out.println(item));
    }

    @Test
    void findByPriceLessThanOrderByPriceDescTest() {
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(30000);
        itemList.forEach(System.out::println);
    }

    @Test
    void findItemDetailTest() {
        List<Item> itemList = itemRepository.findItemDetail("상세");
        itemList.forEach(System.out::println);
    }

    @Test
    void findItemDetailNativeTest() {
        List<Item> itemList = itemRepository.findItemDetailNative("추가");
        itemList.forEach(System.out::println);
    }

    public void createItemList() {
        for (int i = 1; i <= 10; i++) {
            Item item = Item.builder()
                    .id((long) i)
                    .itemNm("테스트 상품" + i)
                    .price(10000 * i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }
    }

    public void createItemList2() {
        for (int i = 1; i <= 5; i++) {
            Item item = Item.builder()
//                    .id((long) i)
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }
        for (int i = 6; i <= 10; i++) {
            Item item = Item.builder()
//                    .id((long) i)
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명 " + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .build();
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("querydsl 테스트")
    public void querydslTest() {
        createItemList();
        JPAQueryFactory query = new JPAQueryFactory(em);
//        QItem qItem = item;

//        query.select(QItem.item).from(QItem.item).where().orderBy().fetch();
        List<Item> itemList = query.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.itemDetail.like("%상세%"))
                .orderBy(item.price.desc())
                .fetch();

        itemList.forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl 테스트2")
    public void querydslTest2() {
        createItemList2();

        BooleanBuilder builder = new BooleanBuilder();
//        QItem item = QItem.item;
        String itemDetail = "테스트";
        int price = 10002;
        String itemSellStatus = "SELL";

        builder.and(item.itemDetail.like("%" + itemDetail + "%"));
        builder.and(item.price.gt(price));

        if (StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5); // 도메인에 있는 거 임포트

        Page<Item> page = itemRepository.findAll(builder, pageable);
        List<Item> content = page.getContent();
        content.forEach(System.out::println);
    }

}