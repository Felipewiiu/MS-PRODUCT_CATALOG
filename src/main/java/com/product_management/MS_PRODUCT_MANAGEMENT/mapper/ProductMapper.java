package com.product_management.MS_PRODUCT_MANAGEMENT.mapper;


import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<Product> toEntity(List<ProductDto> productDto);

    List<ProductDto> toDto(List<Product> product);
}
