package spring_boot_study.spring_boot_study.practice.signup.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import spring_boot_study.spring_boot_study.practice.signup.validation.ValidPassword;

public record SignupRequestDto(@NotBlank(message = "이메일은 필수입니다.") @Email(message = "이메일 형식이 올바르지 않습니다.") String email,
                               // 필드 레벨 커스텀 제약
                               @NotBlank(message = "비밀번호는 필수입니다.") @ValidPassword String password,
                               @NotBlank(message = "비밀번호 확인은 필수입니다.") String passwordConfirm,
                               // 이게 없으면 AddressDto 내부 필드는 검증되지 않고 그냥 통과됨
                               @Valid AddressDto address) {
}
