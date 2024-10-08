package inhatc.cse.ksh.suhyeonshop.member.service;

import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import inhatc.cse.ksh.suhyeonshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    // requiredargsconstructor와 final 키워드로 Bean을 주입받음, test에서는 autowired 사
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        Member newMember = memberRepository.save(member);
        return newMember;
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> mem = memberRepository.findByEmail(member.getEmail()); // optional로 받아옴
//        if (mem.isPresent()) {
//            Member m = mem.get();
//            System.out.println(m);
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }

        Member m2 = memberRepository.findByEmail(member.getEmail()).
                orElseThrow(() -> new IllegalStateException("이미 존재하는 회원입니다."));
        System.out.println(m2);
    }
}
