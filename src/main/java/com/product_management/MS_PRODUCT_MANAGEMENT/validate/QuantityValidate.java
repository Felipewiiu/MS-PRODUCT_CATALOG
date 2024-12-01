package com.product_management.MS_PRODUCT_MANAGEMENT.validate;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class QuantityValidate implements StockValidate{
    @Override
    public Product validateStock(Product product, Integer quantity) {

        if (quantity < 0) {
            throw new ValidationException("Quantity cannot be negative");
        }

        if (quantity > product.getStockQuantity()) {
            throw new ValidationException("There is no balance in sufficient stock");
        }

        return product;
    }
}
