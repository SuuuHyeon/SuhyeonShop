package inhatc.cse.ksh.suhyeonshop.hello.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        HelloDto helloDto = new HelloDto();
        helloDto.setName("김수현");
        helloDto.getName();
        System.out.println(helloDto);
//        log

    }
}
