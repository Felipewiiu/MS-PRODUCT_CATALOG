package com.product_management.MS_PRODUCT_MANAGEMENT.repository;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductCode(Long id);

    Optional<Object> deleteByProductCode(Long id);
}
