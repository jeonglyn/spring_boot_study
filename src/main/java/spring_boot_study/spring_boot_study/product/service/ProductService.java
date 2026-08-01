package spring_boot_study.spring_boot_study.product.service;

import org.springframework.stereotype.Service;
import spring_boot_study.spring_boot_study.product.domain.Product;
import spring_boot_study.spring_boot_study.product.dto.ProductRequestDto;
import spring_boot_study.spring_boot_study.product.dto.ProductResponseDto;
import spring_boot_study.spring_boot_study.product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto insertProduct(ProductRequestDto requestDto) {
        Product product = new Product(null, requestDto.name(), requestDto.price(), requestDto.stock(), requestDto.category());
        Product insert = productRepository.insert(product);

        return ProductResponseDto.from(insert);
    }

    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 물품입니다: " + id));
        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    // 재고 수량만 수정하기
    public ProductResponseDto updateProduct(Long id, Long stock) {
        // 1단계 : 기존 상품을 먼저 찾아야함 (id 이용)
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 물품입니다." + id));

        // 2단계 : 기존 값은 그대로 두고, stock만 새 값으로 교체
        Product updated = new Product(product.getId(), product.getName(), product.getPrice(), stock, product.getCategory());

        // 3단계 : update로 저장
        Product saved = productRepository.update(updated);
        return ProductResponseDto.from(saved);
    }
}
