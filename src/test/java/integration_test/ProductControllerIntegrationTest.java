package integration_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.MsProductManagementApplication;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = MsProductManagementApplication.class)
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "/products";
    }

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetProductById() throws Exception {
        ProductDto productDto = new ProductDto("ELETRONICOS", "name1", "description", 10.0, 5, null, null, 0.0);
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        Long productId = 1L;
        mockMvc.perform(get(baseUrl + "/" + productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testCreateProduct() throws Exception {
        ProductDto productDto = new ProductDto("ELETRONICOS", "name2", "description", 10.0, 5, null, null, 0.0);
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(productDto.name()));
    }

    @Test
    void testSubtractProductStock() throws Exception {
        ProductDto productDto = new ProductDto("ELETRONICOS", "name3", "description", 10.0, 5, null, null, 0.0);
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        Long productId = 1L; // Assuming the product ID is 1 for simplicity
        mockMvc.perform(put(baseUrl + "/" + productId + "/2"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductDto productDto = new ProductDto("ELETRONICOS", "name4", "description", 10.0, 5, null, null, 0.0);
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        Long productId = 1L; // Assuming the product ID is 1 for simplicity
        ProductDto updatedProductDto = new ProductDto("PAPELARIA", "updatedName", "updatedDescription", 20.0, 10, null, null, 5.0);
        String updatedProductJson = objectMapper.writeValueAsString(updatedProductDto);

        mockMvc.perform(put(baseUrl + "/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedProductDto.name()));
    }

    @Test
    void testDeleteProduct() throws Exception {
        ProductDto productDto = new ProductDto("ELETRONICOS", "name5", "description", 10.0, 5, null, null, 0.0);
        String productJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());

        Long productId = 1L; // Assuming the product ID is 1 for simplicity
        mockMvc.perform(delete(baseUrl + "/" + productId))
                .andExpect(status().isNoContent());
    }
}