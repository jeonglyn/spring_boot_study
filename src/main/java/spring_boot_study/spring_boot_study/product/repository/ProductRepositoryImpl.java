package spring_boot_study.spring_boot_study.product.repository;

import org.springframework.stereotype.Repository;
import spring_boot_study.spring_boot_study.product.domain.Product;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Product insert(Product product) {
        Long id = (product.getId() == null) ? nextId++ : product.getId();
        Product inserted = new Product(id, product.getName(), product.getPrice(), product.getStock(), product.getCategory());
        products.put(id, inserted);
        return inserted;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void delete(Long id) {
        products.remove(id);
    }

    @Override
    public Product update(Product product) {
        products.put(product);
        return updated;
    }
}
