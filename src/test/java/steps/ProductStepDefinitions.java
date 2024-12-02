package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDto;
import com.product_management.MS_PRODUCT_MANAGEMENT.dto.ProductDetailDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDto productDto;
    private String responseContent;
    private Long productId;
    private int responseStatus;

    @Given("a product with the following details")
    public void a_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        var data = dataTable.asMaps().get(0);
        productDto = new ProductDto(
                data.get("category"),
                data.get("name"),
                data.get("description"),
                Double.parseDouble(data.get("price")),
                Integer.parseInt(data.get("stockQuantity")),
                null,
                null,
                Double.parseDouble(data.get("discountAmount"))
        );
    }

    @Given("the product is created")
    public void the_product_is_created() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);
        MvcResult result = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andReturn();
        responseContent = result.getResponse().getContentAsString();
        responseStatus = result.getResponse().getStatus();
        ProductDetailDto createdProduct = objectMapper.readValue(responseContent, ProductDetailDto.class);
        productId = createdProduct.productCode();
    }

    @When("the client creates the product")
    public void the_client_creates_the_product() throws Exception {
        String productJson = objectMapper.writeValueAsString(productDto);
        MvcResult result = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andReturn();
        responseContent = result.getResponse().getContentAsString();
        responseStatus = result.getResponse().getStatus();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer status) {
        assertEquals(status.intValue(), responseStatus);
    }

    @Then("the error message should be {string}")
    public void the_error_message_should_be(String errorMessage) throws Exception {
        String actualErrorMessage = objectMapper.readTree(responseContent).get("error").asText();
        assertEquals(errorMessage, actualErrorMessage);
    }

    @When("the client retrieves the product by ID")
    public void the_client_retrieves_the_product_by_id() throws Exception {
        MvcResult result = mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andReturn();
        responseContent = result.getResponse().getContentAsString();
        responseStatus = result.getResponse().getStatus();
    }

    @When("the client updates the existing product with the following details")
    public void the_client_updates_the_existing_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
        var data = dataTable.asMaps().get(0);
        ProductDto updatedProductDto = new ProductDto(
                data.get("category"),
                data.get("name"),
                data.get("description"),
                Double.parseDouble(data.get("price")),
                Integer.parseInt(data.get("stockQuantity")),
                null,
                null,
                Double.parseDouble(data.get("discountAmount"))
        );
        String updatedProductJson = objectMapper.writeValueAsString(updatedProductDto);
        MvcResult result = mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andReturn();
        responseContent = result.getResponse().getContentAsString();
        responseStatus = result.getResponse().getStatus();
    }

    @When("the client removes the product by ID")
    public void the_client_removes_the_product_by_id() throws Exception {
        MvcResult result = mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isNoContent())
                .andReturn();
        responseStatus = result.getResponse().getStatus();
    }

    @Then("the product name should be {string}")
    public void the_product_name_should_be(String name) throws Exception {
        ProductDetailDto createdProduct = objectMapper.readValue(responseContent, ProductDetailDto.class);
        assertEquals(name, createdProduct.name());
    }
}