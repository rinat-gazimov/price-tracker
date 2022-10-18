package ru.tracker.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.tracker.database.mapper.CategoryMapper;
import ru.tracker.database.mapper.ProductMapper;
import ru.tracker.database.model.Category;
import ru.tracker.database.model.Product;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final CategoryMapper mapper;

    public List<Category> getCategories() {
        return mapper.getCategories();
    }

}
