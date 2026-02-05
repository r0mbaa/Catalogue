package ru.spring.manager.service;

import ru.spring.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Product createProduct(String title, String details, Integer price, Integer quantity);

    Optional<Product> findProductById(int productId);

    void updateProduct(Integer id, String title, String details, Integer price, Integer quantity);

    void deleteProduct(Integer id);
}
