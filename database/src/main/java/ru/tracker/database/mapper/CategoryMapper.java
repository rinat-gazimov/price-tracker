package ru.tracker.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.tracker.database.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategories();

}
