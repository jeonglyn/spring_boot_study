package spring_boot_study.spring_boot_study.practice.signup.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import spring_boot_study.spring_boot_study.practice.signup.dto.SignupRequestDto;

// 검증 대상 타입이 String이 아니라 SignupRequestDto인 것에 주목해야한다.
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SignupRequestDto> {
    @Override
    public boolean isValid(SignupRequestDto dto, ConstraintValidatorContext context) {
        if(dto.password() == null || dto.passwordConfirm() == null) {
            return true;
        }

        return dto.password().equals(dto.passwordConfirm());
    }

}

