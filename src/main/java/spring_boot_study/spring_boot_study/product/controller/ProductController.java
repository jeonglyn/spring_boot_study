package spring_boot_study.spring_boot_study.product.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<ProductResponseDto> insert(@Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto response = productService.insertProduct(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductResponseDto>  updateStock(@PathVariable Long id, @Valid @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto update = productService.updateProduct(id, requestDto.stock());
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
