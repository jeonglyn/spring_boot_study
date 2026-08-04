package spring_boot_study.spring_boot_study.practice.event.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import spring_boot_study.spring_boot_study.practice.event.dto.EventRegistrationRequestDto;

/*
 * TODO: isValid()를 직접 작성하세요.
 *
 * 요구사항
 * - startDate 또는 endDate가 null이면 통과시키세요. (null 체크는 @NotNull의 책임)
 * - 둘 다 null이 아니면, startDate가 endDate보다 이전인지 확인하세요. (같은 날짜는 실패로 처리)
 *
 * 힌트: LocalDate에는 isBefore(), isAfter() 메서드가 있습니다.
 */
public class DateRangeValidator implements ConstraintValidator<DateRangeValid, EventRegistrationRequestDto> {

    @Override
    public boolean isValid(EventRegistrationRequestDto dto, ConstraintValidatorContext context) {
        // TODO: 여기에 로직을 작성하세요.
//        throw new UnsupportedOperationException("TODO: isValid를 구현하세요.");

        if(dto.startDate() == null || dto.endDate() == null) return true;

        return dto.startDate().isBefore(dto.endDate());
    }
}
