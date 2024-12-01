package com.product_management.MS_PRODUCT_MANAGEMENT.service.impl;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.repository.ProductRepository;
import com.product_management.MS_PRODUCT_MANAGEMENT.service.ProductService;
import com.product_management.MS_PRODUCT_MANAGEMENT.validate.ProductValidate;
import com.product_management.MS_PRODUCT_MANAGEMENT.validate.StockValidate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServerImpl implements ProductService {

    private final ProductRepository productRepository;
    private final List<ProductValidate> productValidate;
    private final List<StockValidate> stockValidate;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findByProductCode(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteByProductCode(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productEntity = productRepository.findByProductCode(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productEntity.updateProduct(product);

        return productRepository.save(productEntity);
    }

    @Override
    public Product addProduct(Product product) {

        productValidate.forEach(productValidate -> productValidate.validateProduct(product));

        return productRepository.save(product);
    }

    @Override
    public Product debitQuantityProduct(Long productId, Integer quantity) {

        Product productEntity = productRepository.findByProductCode(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        stockValidate.forEach(stockValidate -> stockValidate.validateStock(productEntity, quantity));

        Integer quantityOnStock = productEntity.getStockQuantity();

        Integer newQuantity = quantityOnStock - quantity;

        productEntity.setStockQuantity(newQuantity);

        return productRepository.save(productEntity);

    }
}
