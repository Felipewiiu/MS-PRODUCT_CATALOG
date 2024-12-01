package com.product_management.MS_PRODUCT_MANAGEMENT.mapper;


import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);
}
