package configCucumber;

import com.product_management.MS_PRODUCT_MANAGEMENT.MsProductManagementApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = MsProductManagementApplication.class)
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {
}