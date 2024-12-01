package com.product_management.MS_PRODUCT_MANAGEMENT.service;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public void deleteProductById(Long id);

    public Product updateProduct(Long id, Product product);

    public Product addProduct(Product product);

    public Product debitQuantityProduct(Long productId, Integer quantity);
}
