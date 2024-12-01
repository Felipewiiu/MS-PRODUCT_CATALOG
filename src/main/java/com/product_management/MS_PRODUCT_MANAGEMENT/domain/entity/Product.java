package com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.product_management.MS_PRODUCT_MANAGEMENT.domain.enums.Category;
import jakarta.persistence.*;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCT")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCode;

    @Enumerated(EnumType.ORDINAL)
    private Category category;

    private String name;

    private String description;

    private Double price;

    private Integer stockQuantity;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private Double discountAmount;

    public void updateProduct(Product product) {

        if (productCode != product.getProductCode()) {
            this.productCode = this.productCode;
        }

        if (product.getCategory() != null) {
            this.category = product.getCategory();
        }

        if (product.getName() != null) {
            this.name = product.getName();
        }

        if (product.getDescription() != null) {
            this.description = product.getDescription();
        }

        if (product.getPrice() != null) {
            this.price = product.getPrice();
        }

        if (product.getStockQuantity() != null) {
            this.stockQuantity = product.getStockQuantity();
        }

        if (product.getCreatedAt() != null) {
            this.createdAt = product.getCreatedAt();
        }

        if (product.getUpdatedAt() != null) {
            this.updatedAt = product.getUpdatedAt();
        }

        if (product.getDiscountAmount() != null) {
            this.discountAmount = product.getDiscountAmount();
        }
    }
}
