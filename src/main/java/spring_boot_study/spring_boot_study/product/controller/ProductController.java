package spring_boot_study.spring_boot_study.product.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_study.spring_boot_study.common.ApiResponse;
import spring_boot_study.spring_boot_study.product.dto.ProductRequestDto;
import spring_boot_study.spring_boot_study.product.dto.ProductResponseDto;
import spring_boot_study.spring_boot_study.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> insert(@Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = productService.insertProduct(requestDto);
        return ResponseEntity.status(201).body(ApiResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable Long id) {
        ProductResponseDto data = productService.getProduct(id);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts() {
        List<ProductResponseDto> list = productService.getAllProducts();
        return ResponseEntity.ok(ApiResponse.success(list));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateStock(@PathVariable Long id, @Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto update = productService.updateProduct(id, requestDto.stock());
        return ResponseEntity.ok(ApiResponse.success(update, "재고가 수정되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success(null, "삭제되었습니다."));
    }

}
