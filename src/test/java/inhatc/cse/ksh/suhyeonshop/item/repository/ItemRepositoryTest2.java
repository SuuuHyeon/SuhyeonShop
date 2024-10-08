package inhatc.cse.ksh.suhyeonshop.item.repository;

import inhatc.cse.ksh.suhyeonshop.item.constant.ItemSellStatus;
import inhatc.cse.ksh.suhyeonshop.item.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest2 {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("디스플레이 이름")
    public void insertItemTest() {
        Item item = Item.builder()
                .itemNm("추가 상품")
                .itemDetail("추가 상품 상세 설명")
                .price(20000)
                .stockNumber(300)
                .itemSellStatus(ItemSellStatus.SELL).
                build();
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem);
        assertEquals(savedItem.getId(), 10);
    }

    @Test
    public void findByItemNmTest() {
        List<Item> itemNmList = itemRepository.findByItemNm("추가 상품");
        // 1
        for (Item item : itemNmList) {
            System.out.println(item);
        }
        // 2
        itemNmList.forEach(
                item -> System.out.println(item) // 람다 표기법
        );
        // 3
        itemNmList.forEach(System.out::println);
    }

}