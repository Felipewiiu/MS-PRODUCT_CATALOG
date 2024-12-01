package com.product_management.MS_PRODUCT_MANAGEMENT.dto;

import java.time.LocalDateTime;

public record ProductDetailDto(
        Long productCode,

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
