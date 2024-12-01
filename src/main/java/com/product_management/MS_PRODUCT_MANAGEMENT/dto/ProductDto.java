package com.product_management.MS_PRODUCT_MANAGEMENT.dto;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.enums.Category;

import java.time.LocalDateTime;

public record ProductDto(

        Long product_code,

        Category category,

        String name,

        String description,

        Double price,

        Integer stock_quantity,

        LocalDateTime created_at,

        LocalDateTime updated_at,

        Double discount_amount
) {
}
