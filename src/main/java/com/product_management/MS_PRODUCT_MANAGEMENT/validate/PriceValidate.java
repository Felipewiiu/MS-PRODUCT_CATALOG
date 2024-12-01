package com.product_management.MS_PRODUCT_MANAGEMENT.validate;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PriceValidate implements ProductValidate{
    @Override
    public Product validateProduct(Product product) {

        if(product.getPrice() < 0){
            throw new ValidationException("product cannot have a price lower than zero");
        }

        return product;
    }
}
