package unit_test;

import com.product_management.MS_PRODUCT_MANAGEMENT.controller.ProductController;
import com.product_management.MS_PRODUCT_MANAGEMENT.domain.entity.Product;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDetailDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.mapper.ProductDetailDtoMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.mapper.ProductMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import productTemplateDto.ProductTemplateDto;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductDetailDtoMapper productDetailDtoMapper;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product product = new Product();
        ProductDetailDto productDetailDto = ProductTemplateDto.productDetailTemplate(1L);
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));
        when(productDetailDtoMapper.toDto(product)).thenReturn(productDetailDto);

        ResponseEntity<List<ProductDetailDto>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        ProductDetailDto productDetailDto = ProductTemplateDto.productDetailTemplate(1L);
        when(productService.getProductById(1L)).thenReturn(product);
        when(productDetailDtoMapper.toDto(product)).thenReturn(productDetailDto);

        ResponseEntity<ProductDetailDto> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDetailDto, response.getBody());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testCreateProduct() {
        ProductDto productDto = ProductTemplateDto.productTemplate();
        Product product = new Product();
        ProductDetailDto productDetailDto = ProductTemplateDto.productDetailTemplate(1L);
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productService.addProduct(product)).thenReturn(product);
        when(productDetailDtoMapper.toDto(product)).thenReturn(productDetailDto);

        ResponseEntity<ProductDetailDto> response = productController.createProduct(productDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDetailDto, response.getBody());
        verify(productService, times(1)).addProduct(product);
    }

    @Test
    void testSubtractProductStock() {
        Product product = new Product();
        ProductDetailDto productDetailDto = ProductTemplateDto.productDetailTemplate(1L);
        when(productService.debitQuantityProduct(1L, 2)).thenReturn(product);
        when(productDetailDtoMapper.toDto(product)).thenReturn(productDetailDto);

        ResponseEntity<ProductDetailDto> response = productController.subtractProductStock(1L, 2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDetailDto, response.getBody());
        verify(productService, times(1)).debitQuantityProduct(1L, 2);
    }

    @Test
    void testUpdateProduct() {
        ProductDto productDto = ProductTemplateDto.productTemplate();
        Product product = new Product();
        ProductDetailDto productDetailDto = ProductTemplateDto.productDetailTemplate(1L);
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productService.updateProduct(1L, product)).thenReturn(product);
        when(productDetailDtoMapper.toDto(product)).thenReturn(productDetailDto);

        ResponseEntity<ProductDetailDto> response = productController.updateProduct(1L, productDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDetailDto, response.getBody());
        verify(productService, times(1)).updateProduct(1L, product);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productService).deleteProductById(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(1L);
    }
}