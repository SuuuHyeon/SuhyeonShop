package inhatc.cse.ksh.suhyeonshop.member.service;

import inhatc.cse.ksh.suhyeonshop.member.dto.MemberDto;
import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("회원가입 테스트")
    void saveMemberTest() {
        Member member = createMember();
//        System.out.println(member);
        Member saveMember = memberService.saveMember(member);
//        System.out.println(saveMember);
        assertEquals(member.getEmail(), saveMember.getEmail());
    }

    private Member createMember() {
        MemberDto memberDto = MemberDto.builder()
                .address("인천 미추홀구")
                .email("test@test.com")
                .name("길동이")
                .password("1111")
                .build();
        System.out.println(memberDto);
        Member member = Member.createMember(memberDto, passwordEncoder);
        return member;
    }
}