package com.product_management.MS_PRODUCT_MANAGEMENT.mapper;


import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailDtoMapper {

    ProductDetailDto toDto(Product product);

    Product toEntity(ProductDetailDto dto);
}
