package ru.tracker.integration.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.tracker.database.repository.CategoryRepository;
import ru.tracker.integration.client.TrackerClient;
import ru.tracker.integration.model.BodyDto;
import ru.tracker.integration.model.ProductDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TrackerService {

    private final TrackerClient client;
    private final CategoryRepository categoryRepository;

    public BodyDto getProductIds(int categoryId){
        BodyDto bodyDto = null;
        try {
            bodyDto = client.getProducts(categoryId).getBody();
        } catch (Exception e) {
            System.out.println(e);
        }

        return bodyDto;
    }

    public List<ProductDto> getDetailedProducts(List<String> productIds) {
        List<ProductDto> list = null;
        try {
            list = client.getDetailedProducts(productIds);;
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

//    private void saveProducts() {
//        List<Category> categories = categoryRepository.getCategories();
//        categories.stream().map(cat -> {
//
//        })
//
//
//        List<BodyDto> bodyDtos = categories.stream().map(o -> getProductIds(o.getExtId())).collect(Collectors.toList());
//        List<ProductDto> products = bo
//
//
//        List<ProductDto> products = getDetailedProducts(getProductIds());
//

//    }

}
