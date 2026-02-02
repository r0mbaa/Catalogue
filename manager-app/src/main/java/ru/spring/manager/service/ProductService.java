package ru.spring.manager.service;

import ru.spring.manager.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();
}
