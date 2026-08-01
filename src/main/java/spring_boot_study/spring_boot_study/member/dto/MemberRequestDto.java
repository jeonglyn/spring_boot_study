package spring_boot_study.spring_boot_study.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// 클라이언트 -> 서버로 들어오는 요청 데이터 전용 DTO
// record를 사용하면 불변(immutable) 데이터 전달 객체를 간결하게 표현 가능 (Java 16)
public record MemberRequestDto(@NotBlank(message = "이름은 필수입니다") String name,
                               @NotBlank(message = "이메일은 필수입니다")
                               @Email(message = "이메일 형식이 올바르지 않습니다") String email) {}
