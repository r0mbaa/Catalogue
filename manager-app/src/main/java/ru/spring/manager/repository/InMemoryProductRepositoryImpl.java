package ru.spring.manager.repository;


import org.springframework.stereotype.Repository;
import ru.spring.manager.entity.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public InMemoryProductRepositoryImpl(){
        IntStream.range(1,10)
                .forEach(i -> this.products.add(new Product(i,"item #%d".formatted(i),"item details #%d".formatted(i), i * 10, i*100)));
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.products);
    }
}
