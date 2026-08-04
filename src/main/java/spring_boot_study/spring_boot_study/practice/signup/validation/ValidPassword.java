package spring_boot_study.spring_boot_study.practice.signup.validation;

// 커스텀 필드 제약 어노테이션 - "영문과 숫자를 모두 포함해야 한다"는 도메인 규칙
// @NotBlank처럼 필드 위에 직접 붙여서 쓸 수 있게 만드는 것이 목표

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)          // @Target     : 어노테이션을 어디에 붙일 수 있는지 정한다. ex) FIELD(필드), METHOD(메소드), PARAMETER(파라미터) 등
@Retention(RetentionPolicy.RUNTIME) // @Retention  : 어노테이션이 언제까지 살아있는지 정한다. ex) RUNTIME (실행 시점에도 어노테이션 정보가 남아있어야함)
@Constraint(validatedBy = PasswordValidator.class)  // @Constraint : 어노테이션이 유효성 검증용임을 나타낸다. 실제로 검증을 수행할 클래스를 지정한다.
public @interface ValidPassword {
    // 검증 실패 시 기본 메시지 (Bean Validation 규약상 반드시 있어야 하는 속성)
    String message() default "비밀번호는 영문과 숫자를 모두 포함해야 합니다.";

    // 아래 두 속성도 Bean Validation 규약상 필수 (그룹, 부가 메타데이터용, 지금은 기본값만 사용)
    // Class<?> => 자바의 제네릭 와일드카드 문법으로, 어떤 종류든 상관없이 모든 클래스 타입을 다 받을 수 있다 라는 뜻
    // Bean Validation에서 필수인 이유 : 개발자가 검증 그룹을 이지정하기 위해 어떤 인터페이스나 클래스를 만들지 모르기 때문에..
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
