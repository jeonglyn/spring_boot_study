package spring_boot_study.spring_boot_study.practice.event.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
 * TODO: isValid()를 직접 작성하세요.
 *
 * 요구사항
 * - couponCode는 선택 필드입니다. null이면 통과시키세요. (null 체크는 이 검증기의 책임이 아님 - @NotBlank가 아니므로)
 * - null이 아니라면, "CP-" 로 시작하고 그 뒤에 숫자 4자리가 와야 합니다. (예: CP-1234는 통과, CP-12나 CPX-1234는 실패)
 *
 * 힌트: 정규식을 쓰면 한 줄로 끝납니다.
 *   code.matches("CP-\\d{4}")
 */
public class CouponCodeValidator implements ConstraintValidator<ValidCouponCode, String> {

    @Override
    public boolean isValid(String couponCode, ConstraintValidatorContext context) {
        // TODO: 여기에 로직을 작성하세요.
        //throw new UnsupportedOperationException("TODO: isValid를 구현하세요.");

        if(couponCode == null) return true;
        boolean result = couponCode.matches("CP-\\d{4}");

        return result;
    }
}
