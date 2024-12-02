package unit_test;

import com.product_management.MS_PRODUCT_MANAGEMENT.exceptionhandler.StandardError;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardErrorTest {

    @Test
    public void testStandardError() {
        Instant now = Instant.now();
        StandardError error = new StandardError(now, 400, "Bad Request", "Validation error", "/test");

        assertEquals(now, error.getTimestamp());
        assertEquals(400, error.getStatus());
        assertEquals("Bad Request", error.getError());
        assertEquals("Validation error", error.getMessage());
        assertEquals("/test", error.getPath());
    }
}