package spring_boot_study.spring_boot_study.practice.signup.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(@NotBlank(message = "우편번호는 필수입니다.") String zipCode,
                         @NotBlank(message = "상세 주소는 필수입니다.") String detail) {
}
