package inhatc.cse.ksh.suhyeonshop.member.controller;


import inhatc.cse.ksh.suhyeonshop.member.dto.MemberDto;
import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import inhatc.cse.ksh.suhyeonshop.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService; // 자동으로 객체 생성
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 패스워드를 확인해주세요.");
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("============== logout ==============");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 인증정보
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    // 회원가입
    @GetMapping("/add")
    public String newMember(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/add";
    }

    @PostMapping("/add")
    public String addMember(@Valid MemberDto memberDto,  // form에서 넘어온 데이터
                            BindingResult bindingResult, // binding 결과
                            Model model) {               // 데이터

        // 에러체크
        if (bindingResult.hasErrors()) {
            return "member/add";
        }

        try {
        Member member = Member.createMember(memberDto, passwordEncoder);
        Member savedMember = memberService.saveMember(member);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/add";
        }

        return "redirect:/";
    }
}
