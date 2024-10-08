package inhatc.cse.ksh.suhyeonshop.hello.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private int price;
    private String status;
}
