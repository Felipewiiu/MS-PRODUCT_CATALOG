package com.product_management.MS_PRODUCT_MANAGEMENT.config;


import com.product_management.MS_PRODUCT_MANAGEMENT.validate.PriceValidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    public PriceValidate priceValidate() {
        return new PriceValidate();
    }
}
