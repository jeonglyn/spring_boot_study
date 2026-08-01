package spring_boot_study.spring_boot_study.member.service;

import spring_boot_study.spring_boot_study.member.domain.Member;
import spring_boot_study.spring_boot_study.member.dto.MemberRequestDto;
import spring_boot_study.spring_boot_study.member.dto.MemberResponseDto;
import spring_boot_study.spring_boot_study.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 비즈니스 로직을 담당하는 계층
// Controller와 Repository 사이에서 DTO <-> Entity 변환, 검증, 트랜잭션을 조율
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자 주입 (필드 주입 @Autowired 대신 생성자 주입을 권장 -
    // 불변성 보장, 순환참조를 컴파일 타임에 발견 가능)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        // 요청 DTO -> 도메인 객체로 변환
        Member member = new Member(null, requestDto.name(), requestDto.email());
        Member saved = memberRepository.save(member);
        // 도메인 객체 -> 응답 DTO로 변환해서 반환
        return MemberResponseDto.from(saved);
    }

    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다: " + id));
        return MemberResponseDto.from(member);
    }

    public List<MemberResponseDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberResponseDto::from)
                .toList();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
