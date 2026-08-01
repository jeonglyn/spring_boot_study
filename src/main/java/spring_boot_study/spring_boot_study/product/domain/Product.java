package spring_boot_study.spring_boot_study.product.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Product {
    private Long id;
    private String name;
    private Long price;
    private Long stock;
    private String category;
    private LocalDateTime createdAt;

    // 생성 시점에 필수 값만 받는 생성자
    public Product(Long id, String name, Long price, Long stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

}
