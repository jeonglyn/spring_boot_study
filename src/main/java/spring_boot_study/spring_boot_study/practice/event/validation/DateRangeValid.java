package spring_boot_study.spring_boot_study.practice.event.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * TODO: 어노테이션 선언부(interface 위)에 붙일 3가지를 직접 작성하세요.
 *
 * 1) @Target(...) - ValidCouponCode 때와 다른 값이 필요합니다.
 *    - 이건 필드 하나가 아니라 EventRegistrationRequestDto 클래스 전체 위에 붙일 거라서
 *      ElementType.FIELD가 아니라 ElementType.TYPE 이어야 합니다.
 *
 * 2) @Retention(...) - ValidCouponCode 때와 이유가 동일합니다. (RUNTIME)
 *
 * 3) @Constraint(validatedBy = ...) - 이번엔 DateRangeValidator.class 를 지정하세요.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
public @interface DateRangeValid {
    String message() default "종료일은 시작일보다 이후여야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
