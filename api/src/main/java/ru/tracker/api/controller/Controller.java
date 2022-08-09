package ru.tracker.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tracker.database.service.ProductService;
import ru.tracker.integration.BodyDto;
import ru.tracker.integration.ProductDto;
import ru.tracker.integration.service.TrackerService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Controller {

    private final TrackerService service;

    @Operation()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400")})
    @GetMapping("/{categoryId}")
    BodyDto productIds(
            @Parameter(in = ParameterIn.PATH, description = "category id", required = true)
            @PathVariable("categoryId") String categoryId) throws IOException {
        return service.getProductIds(categoryId);
    }

    @Operation()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400")})
    @PostMapping("/products")
    List<ProductDto> productDetails(@RequestBody List<String> categoryIds) throws IOException {
        return service.getDetailedProducts(categoryIds);
    }

}
