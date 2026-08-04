package spring_boot_study.spring_boot_study.practice.event.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import spring_boot_study.spring_boot_study.practice.event.validation.DateRangeValid;
import spring_boot_study.spring_boot_study.practice.event.validation.ValidCouponCode;

import java.time.LocalDate;

/*
 * TODO: 아래 3가지를 직접 채워보세요. import는 이미 다 되어 있습니다.
 *
 * 1) 클래스 선언(public record EventRegistrationRequestDto( 바로 위)에 @DateRangeValid를 붙이세요.
 *    - 클래스 레벨 제약이라 필드가 아니라 record 선언부 위에 붙는다는 점에 주의하세요.
 *
 * 2) applicant 필드에 @Valid를 붙이세요.
 *    - 이걸 빼먹으면 ApplicantDto 내부의 @NotBlank/@Email이 전혀 동작하지 않습니다.
 *
 * 3) couponCode 필드에 @ValidCouponCode를 붙이세요.
 *    - couponCode는 선택 필드이므로 @NotBlank는 붙이지 않습니다.
 */
@DateRangeValid
public record EventRegistrationRequestDto(

        @NotBlank(message = "행사명은 필수입니다.")
        String eventName,

        // TODO: 여기에 @Valid를 붙이세요.
        @Valid
        ApplicantDto applicant,

        @NotNull(message = "시작일은 필수입니다.")
        LocalDate startDate,

        @NotNull(message = "종료일은 필수입니다.")
        LocalDate endDate,

        // TODO: 여기에 @ValidCouponCode를 붙이세요.
        @ValidCouponCode
        String couponCode
) {
}
