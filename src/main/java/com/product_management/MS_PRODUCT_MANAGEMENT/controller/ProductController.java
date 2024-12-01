package com.product_management.MS_PRODUCT_MANAGEMENT.controller;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.mapper.ProductMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        List<ProductDto> productDtoList = productMapper.toDto(productList);

        return ResponseEntity.ok(productDtoList);
    }
}
