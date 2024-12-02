package productTemplateDto;

import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDetailDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;

import java.time.LocalDateTime;

public class ProductTemplateDto {

    public static ProductDto productTemplate() {
        return new ProductDto(
                "ELETRONICOS",
                "name",
                "description",
                10.0,
                5,
                LocalDateTime.now(),
                LocalDateTime.now(),
                0.0
        );
    }

    public static ProductDetailDto productDetailTemplate(Long productCode) {
        return new ProductDetailDto(
                productCode,
                "ELETRONICOS",
                "name",
                "description",
                10.0,
                5,
                LocalDateTime.now(),
                LocalDateTime.now(),
                0.0
        );
    }
}