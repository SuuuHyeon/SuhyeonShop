package inhatc.cse.ksh.suhyeonshop.member.service;

import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import inhatc.cse.ksh.suhyeonshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {
    // requiredargsconstructor와 final 키워드로 Bean을 주입받음, test에서는 autowired 사
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        Member newMember = memberRepository.save(member);
        return newMember;
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> mem = memberRepository.findByEmail(member.getEmail()); // optional로 받아옴
        if (mem.isPresent()) {
            Member m = mem.get();
            System.out.println(m);
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

//        Member m2 = memberRepository.findByEmail(member.getEmail()).
//                orElseThrow(() -> new IllegalStateException("이미 존재하는 회원입니다."));
//        System.out.println(m2);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + email));

        log.info(member.toString());

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
