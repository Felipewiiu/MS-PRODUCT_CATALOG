package com.product_management.MS_PRODUCT_MANAGEMENT.dto;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.enums.Category;

import java.time.LocalDateTime;

public record ProductDto(

        String category,

        String name,

        String description,

        Double price,

        Integer stockQuantity,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Double discountAmount
) {
}
