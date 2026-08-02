package spring_boot_study.spring_boot_study.practice.point.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// 포인트 사용 요청 - 클라이언트가 "얼마를 사용할지"만 보내면 된다
public record PointRequestDto(
        @NotNull(message = "사용 금액은 필수입니다.")
        @Positive(message = "사용 금액은 0보다 커야 합니다.")
        Long amount
) {
}
