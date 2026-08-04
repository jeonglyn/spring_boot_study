package spring_boot_study.spring_boot_study.practice.event.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * TODO: 어노테이션 선언부(interface 위)에 붙일 4가지를 직접 작성하세요.
 * import는 이미 다 되어 있으니, 어떤 어노테이션을 어떤 값으로 붙일지만 채우면 됩니다.
 *
 * 1) @Target(...) - 이 어노테이션을 어디에 붙일 수 있는지 지정
 *    - 필드 위(String couponCode 같은 곳)에 붙일 거니까 ElementType.FIELD
 *
 * 2) @Retention(...) - 이 어노테이션 정보를 언제까지 유지할지 지정
 *    - Bean Validation은 런타임에 리플렉션으로 어노테이션을 읽어서 검증하므로 RetentionPolicy.RUNTIME이어야 함
 *      (CLASS나 SOURCE로 하면 실행 중에 이 어노테이션이 붙어있는지 확인할 수 없어서 동작 안 함)
 *
 * 3) @Constraint(validatedBy = ...) - 실제 검증 로직을 담당할 클래스 지정
 *    - 이 패키지에 있는 CouponCodeValidator.class 를 지정하면 됨
 *
 * 4) interface 이름은 이미 ValidCouponCode로 되어 있음 (건드릴 필요 없음)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CouponCodeValidator.class)
public @interface ValidCouponCode {

    // 아래 3개 메서드는 Bean Validation 규약상 커스텀 제약 어노테이션이라면 반드시 있어야 하는 필수 요소입니다.
    // (이미 작성되어 있으니 그대로 두시면 됩니다 - 값만 자유롭게 바꿔도 됩니다)

    String message() default "쿠폰 코드 형식이 올바르지 않습니다. (예: CP-1234)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
