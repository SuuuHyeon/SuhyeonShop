package inhatc.cse.ksh.suhyeonshop.member.repository;

import inhatc.cse.ksh.suhyeonshop.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
