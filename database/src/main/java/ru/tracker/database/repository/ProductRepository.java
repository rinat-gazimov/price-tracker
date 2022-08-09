package ru.tracker.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.tracker.database.mapper.ProductMapper;
import ru.tracker.database.model.Product;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductMapper mapper;

    public List<Product> getProducts() {
        return mapper.getProducts();
    }

}
