package com.product_management.MS_PRODUCT_MANAGEMENT.service;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(int id);

    public Product deleteProductById(int id);

    public Product updateProduct(Product product);

    public Product addProduct(Product product);

    public Product debitQuantityProduct(Product product, int quantity);
}
