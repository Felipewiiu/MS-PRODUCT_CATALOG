package com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.enums.Category;
import jakarta.persistence.*;
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
    private Long product_code;

    private Category category;

    private String name;

    private String description;

    private Double price;

    private Integer stock_quantity;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private Double discount_amount;
}
