package ru.spring.manager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.manager.entity.Product;
import ru.spring.manager.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }
}
