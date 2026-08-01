package spring_boot_study.spring_boot_study.member.repository;

import spring_boot_study.spring_boot_study.member.domain.Member;
import java.util.List;
import java.util.Optional;

// Repository는 인터페이스로 먼저 설계 (구현체를 나중에 JPA로 교체하기 쉽게)
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    void deleteById(Long id);
}
