package spring_boot_study.spring_boot_study.practice.event.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// 신청자 정보 - 중첩 검증 실습용 DTO
public record ApplicantDto(
        @NotBlank(message = "신청자 이름은 필수입니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email
) {
}
