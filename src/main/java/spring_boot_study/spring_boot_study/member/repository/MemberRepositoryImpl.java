package spring_boot_study.spring_boot_study.member.repository;

import spring_boot_study.spring_boot_study.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 지금은 메모리 저장소 (나중에 JPA로 교체 예정)
// 참고: 실무에서는 동시 요청 시 id가 겹치지 않도록 AtomicLong 같은 걸 쓰지만,
// 지금 단계에서는 몰라도 괜찮음 -> JPA 배우면 DB가 알아서 처리해줌
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private Long nextId = 1L;  // 간단한 카운터

    @Override
    public Member save(Member member) {
        Long id = (member.getId() == null) ? nextId++ : member.getId();
        Member saved = new Member(id, member.getName(), member.getEmail());
        store.put(id, saved);
        return saved;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
