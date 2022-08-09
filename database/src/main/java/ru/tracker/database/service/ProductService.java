package ru.tracker.database.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tracker.database.model.Product;
import ru.tracker.database.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getProducts() {
        return  repository.getProducts();
    }

}
