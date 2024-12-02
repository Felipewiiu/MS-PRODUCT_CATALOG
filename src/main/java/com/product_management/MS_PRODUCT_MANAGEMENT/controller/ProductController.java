package com.product_management.MS_PRODUCT_MANAGEMENT.controller;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDetailDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.mapper.ProductDetailDtoMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.mapper.ProductMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductDetailDtoMapper productDetailDtoMapper;

    @GetMapping
    public ResponseEntity<List<ProductDetailDto>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        List<ProductDetailDto> productDtoList = productList.stream()
                .map(productDetailDtoMapper::toDto).toList();

        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        ProductDetailDto productDetailDto = productDetailDtoMapper.toDto(product);

        return ResponseEntity.ok(productDetailDto);
    }

    @PostMapping
    public ResponseEntity<ProductDetailDto> createProduct(@RequestBody ProductDto productDto) {
       Product product = productService.addProduct(productMapper.toEntity(productDto));

       return ResponseEntity.status(HttpStatus.CREATED).body(productDetailDtoMapper.toDto(product));
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<ProductDetailDto> subtractProductStock(
            @PathVariable Long id,
            @PathVariable Integer quantity) {

        Product product = productService.debitQuantityProduct(id, quantity);

        ProductDetailDto productDetailDto = productDetailDtoMapper.toDto(product);

        return ResponseEntity.ok(productDetailDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(id, productMapper.toEntity(productDto));

        return ResponseEntity.status(HttpStatus.OK).body(productDetailDtoMapper.toDto(product));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
