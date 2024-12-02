package endToEnd_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.MsProductManagementApplication;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDetailDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import productTemplateDto.ProductTemplateDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MsProductManagementApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDto productDto;

    @BeforeEach
    void setUp() throws Exception {
        productDto = ProductTemplateDto.productTemplate();
    }

    @Test
    void testCreateProduct() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);

        String responseContent = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDetailDto actualDto = objectMapper.readValue(responseContent, ProductDetailDto.class);
        assertEquals(productDto.name(), actualDto.name());
    }

    @Test
    void testGetProductById() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        String responseContent = mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDetailDto actualDto = objectMapper.readValue(responseContent, ProductDetailDto.class);
        assertEquals(productDto.name(), actualDto.name());
    }

    @Test
    void testUpdateProduct() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        ProductDto updatedProductDto = new ProductDto("PAPELARIA", "updatedName", "updatedDescription", 20.0, 10, null, null, 5.0);
        String updatedProductJson = objectMapper.writeValueAsString(updatedProductDto);

        String responseContent = mockMvc.perform(put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDetailDto actualDto = objectMapper.readValue(responseContent, ProductDetailDto.class);
        assertEquals(updatedProductDto.name(), actualDto.name());
    }

    @Test
    void testDeleteProduct() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCreateProductWithNegativePrice() throws Exception {
        productDto = new ProductDto(
                "ELETRONICOS",
                "Sample Product",
                "Sample Description",
                -10.0, // Invalid price
                10,
                null,
                null,
                0.0
        );

        String productJson = objectMapper.writeValueAsString(productDto);

        String responseContent = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String errorMessage = objectMapper.readTree(responseContent).get("error").asText();
        assertEquals("product cannot have a price lower than zero", errorMessage);
    }

    @Test
    void testCreateProductWithNegativeStockQuantity() throws Exception {
        productDto = new ProductDto(
                "ELETRONICOS",
                "Sample Product",
                "Sample Description",
                100.0,
                -5, // Invalid stock quantity
                null,
                null,
                0.0
        );

        String productJson = objectMapper.writeValueAsString(productDto);

        String responseContent = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String errorMessage = objectMapper.readTree(responseContent).get("error").asText();
        assertEquals("Stock quantity must be greater than zero", errorMessage);
    }

    @Test
    void testDebitProductStockWithNegativeQuantity() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        String responseContent = mockMvc.perform(put("/products/{id}/{quantity}", 1L, -5)) // Invalid quantity
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String errorMessage = objectMapper.readTree(responseContent).get("error").asText();
        assertEquals("Quantity cannot be negative", errorMessage);
    }

    @Test
    void testDebitProductStockWithExcessiveQuantity() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        String responseContent = mockMvc.perform(put("/products/{id}/{quantity}", 1L, 100)) // Excessive quantity
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String errorMessage = objectMapper.readTree(responseContent).get("error").asText();
        assertEquals("There is no balance in sufficient stock", errorMessage);
    }

}