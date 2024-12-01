package com.product_management.MS_PRODUCT_MANAGEMENT.validate;


import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;

public interface StockValidate {

    public Product validateStock(Product product, Integer quantidade);
}
