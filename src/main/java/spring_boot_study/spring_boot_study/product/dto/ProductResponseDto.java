package spring_boot_study.spring_boot_study.product.dto;

import spring_boot_study.spring_boot_study.product.domain.Product;

import java.time.LocalDateTime;

public record ProductResponseDto(Long id, String name, Long price, Long stock, String category, LocalDateTime createdAt) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getCreatedAt()
        );
    }
}