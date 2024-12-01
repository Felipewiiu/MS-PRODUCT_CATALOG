package com.product_management.MS_PRODUCT_MANAGEMENT.validate;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class StockQuantityOnCreateProductValidate implements ProductValidate{
    @Override
    public Product validateProduct(Product product) {
        if(product.getStockQuantity() < 0){
            throw new ValidationException("Stock quantity must be greater than zero");
        }

        return product;
    }
}
