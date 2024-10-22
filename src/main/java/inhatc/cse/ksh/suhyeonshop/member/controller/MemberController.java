package inhatc.cse.ksh.suhyeonshop.member.controller;


import inhatc.cse.ksh.suhyeonshop.member.dto.MemberDto;
import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import inhatc.cse.ksh.suhyeonshop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService; // 자동으로 객체 생성
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @GetMapping("/add")
    public String newMember(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/add";
    }

    @PostMapping("/add")
    public String addMember(MemberDto memberDto) {
        Member member = Member.createMember(memberDto, passwordEncoder);
        Member savedMember = memberService.saveMember(member);
        System.out.println("저장된 멤버:" + savedMember);
        return "redirect:/";
    }
}
