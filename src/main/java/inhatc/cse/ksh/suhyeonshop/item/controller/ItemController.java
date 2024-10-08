package inhatc.cse.ksh.suhyeonshop.item.controller;

import inhatc.cse.ksh.suhyeonshop.hello.dto.Product;
import inhatc.cse.ksh.suhyeonshop.item.dto.ItemDataDto;
import inhatc.cse.ksh.suhyeonshop.item.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ItemController {

    @GetMapping("/item/thymeleaf1")
    public String thymeleaf1(Model model) {
        ItemDto itemDto = ItemDto.builder()
                .id(1L)
                .itemNm("가을 가디건")
                .itemDetail("가을에만 팔아유")
                .price(25000)
                .stockNumber(200)
                .build();
        model.addAttribute("itemDto", itemDto);
        return "item/thymeleaf1";
    }

    @GetMapping("/item/thymeleaf2")
    public String thymeleaf2(Model model, ItemDataDto itemDataDto) {
        System.out.println("itemDataDto==========>" + itemDataDto);
        model.addAttribute("itemDataDto", itemDataDto);
        return "item/thymeleaf2";
    }

    @GetMapping("/thymeleaf/practice")
    public String practice(Model model) {
        List<Product> products = List.of(
                new Product("상품1", 10000, "SELL"),
                new Product("상품2", 20000, "SOLD OUT"),
                new Product("상품3", 30000, "SELL")
        );
        model.addAttribute("products", products);
        model.addAttribute("showMessage", true);
        model.addAttribute("status", "ㅁㄴㅁㄴ");
        return "item/practice";
    }
}
