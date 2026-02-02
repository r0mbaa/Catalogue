package ru.spring.manager.repository;

import ru.spring.manager.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
