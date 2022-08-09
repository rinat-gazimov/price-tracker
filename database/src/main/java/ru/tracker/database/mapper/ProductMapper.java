package ru.tracker.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.tracker.database.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> getProducts();


}
