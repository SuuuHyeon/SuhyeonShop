package inhatc.cse.ksh.suhyeonshop.hello.controller;

import inhatc.cse.ksh.suhyeonshop.hello.dto.HelloDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
//        HelloDto helloDto = new HelloDto("김수현", 25, "컴시");

        HelloDto helloDto = HelloDto.builder()
                .name("김수현")
                .build();
        model.addAttribute("data", helloDto);
        return "test/test";
    }

}
