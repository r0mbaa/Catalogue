package ru.spring.manager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.manager.entity.Product;
import ru.spring.manager.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details, Integer price, Integer quantity) {
        Product createdProduct = this.productRepository.save(new Product(null, title, details, price, quantity));
        return createdProduct;
    }

    @Override
    public Optional<Product> findProductById(int productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public void updateProduct(Integer id, String title, String details, Integer price, Integer quantity) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                    product.setPrice(price);
                    product.setQuantity(quantity);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }
}
