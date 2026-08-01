package spring_boot_study.spring_boot_study.member.dto;

import spring_boot_study.spring_boot_study.member.domain.Member;
import java.time.LocalDateTime;

// 서버 -> 클라이언트로 나가는 응답 데이터 전용 DTO
// Entity를 그대로 반환하지 않고 이 DTO로 변환해서 내보낸다
public record MemberResponseDto(Long id, String name, String email, LocalDateTime createdAt) {
    // 정적 팩토리 메서드: Entity -> DTO 변환 로직을 DTO 쪽에 두면
    // Service 코드가 깔끔해지고 변환 로직이 한 곳에 모인다
    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getCreatedAt()
        );
    }
}
