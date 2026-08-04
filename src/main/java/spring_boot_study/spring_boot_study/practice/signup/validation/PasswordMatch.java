package spring_boot_study.spring_boot_study.practice.signup.validation;

// 클래스 레벨 제약
// 비밀번호와 비밀번호 확인이 같아야 한다 는 필드 두개를 동시에 봐야함
// 필드가 아니라 클래스 전체에 붙이는 제약으로 만든다.

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)           // 필드가 아니라 클래스(TYPE) 위에 붙임
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
