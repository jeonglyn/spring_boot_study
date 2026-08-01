package spring_boot_study.spring_boot_study.product.repository;

import spring_boot_study.spring_boot_study.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product insert(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void delete(Long id);
    Product update(Product product);
}