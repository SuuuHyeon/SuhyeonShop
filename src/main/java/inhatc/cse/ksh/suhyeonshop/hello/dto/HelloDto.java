package inhatc.cse.ksh.suhyeonshop.hello.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloDto {
    private String name;
    private int grade;
    private String dept;

}
