package ru.tracker.database.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tracker.database.model.Category;
import ru.tracker.database.model.Product;
import ru.tracker.database.repository.CategoryRepository;
import ru.tracker.database.repository.ProductRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getCategories() {
        return  repository.getCategories();
    }

}
