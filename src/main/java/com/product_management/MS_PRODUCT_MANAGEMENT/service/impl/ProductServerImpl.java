package com.product_management.MS_PRODUCT_MANAGEMENT.service.impl;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.repository.ProductRepository;
import com.product_management.MS_PRODUCT_MANAGEMENT.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServerImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();

        return productList;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public Product deleteProductById(int id) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product debitQuantityProduct(Product product, int quantity) {
        return null;
    }
}
