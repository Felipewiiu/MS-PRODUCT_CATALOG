package unit_test;

import com.product_management.MS_PRODUCT_MANAGEMENT.domain.enums.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testCategoryValues() {
        assertEquals(1, Category.ELETRONICOS.getValue());
        assertEquals(2, Category.PAPELARIA.getValue());
        assertEquals(3, Category.VESTUARIO.getValue());
    }

    @Test
    void testCategoryNames() {
        assertEquals("ELETRONICOS", Category.ELETRONICOS.name());
        assertEquals("PAPELARIA", Category.PAPELARIA.name());
        assertEquals("VESTUARIO", Category.VESTUARIO.name());
    }

    @Test
    void testCategoryFromString() {
        assertEquals(Category.ELETRONICOS, Category.valueOf("ELETRONICOS"));
        assertEquals(Category.PAPELARIA, Category.valueOf("PAPELARIA"));
        assertEquals(Category.VESTUARIO, Category.valueOf("VESTUARIO"));
    }
}