package spring_boot_study.spring_boot_study.practice.signup.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// ConstraintValidator => 커스텀 어노테이션이 실제로 어떤 규칙으로 값을 검증할지 구현하는 인터페이스
// ConstraintValidator<A, T> => A : 내가 만든 커스텀 어노테이션 타입, T : 검증할 데이터(값)의 타입
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if(password == null) {
            return true;    // null 체크는 @NotBlank의 역할이라 여기서는 관여하지 않는다.
        }

        boolean hasLetter = password.chars().anyMatch(Character::isLetter);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);

        // password.chars() : 비밀번호 문자열을 글자 한개씩으로 이루어진 흐름(스트림)으로 변환
        // anyMatch() : 이 흐름(스트림) 중에 단 하나라도 조건에 맞는게 있는지 물어보는 함수
        // Character::isLetter : 이게 영어인지 확인
        // Character::isDigit  : 이게 숫자인지 확인

        return hasLetter && hasDigit;
    }

}
