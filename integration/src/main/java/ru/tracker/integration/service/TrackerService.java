package ru.tracker.integration.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.tracker.integration.BodyDto;
import ru.tracker.integration.ProductDto;
import ru.tracker.integration.client.TrackerClient;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TrackerService {

    private final TrackerClient client;

    public BodyDto getProductIds(String categoryId) throws IOException {
        return client.getProducts(categoryId).getBody();
    }

    public List<ProductDto> getDetailedProducts(List<String> productIds) throws IOException {
        return client.getDetailedProducts(productIds);
    }

}
