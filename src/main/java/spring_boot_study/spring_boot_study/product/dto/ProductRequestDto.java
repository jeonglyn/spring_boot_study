package spring_boot_study.spring_boot_study.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequestDto(@NotBlank (message = "이름은 필수입니다.")String name,
                                @Positive (message = "가격은 양수여야 합니다.") Long price,
                                @Min(value = 0, message = "수량은 0 이상이어야 합니다.") Long stock,
                                String category) {
}