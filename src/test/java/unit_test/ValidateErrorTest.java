package unit_test;

import com.product_management.MS_PRODUCT_MANAGEMENT.exceptionhandler.ValidateError;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateErrorTest {

    @Test
    public void testValidateError() {
        Instant now = Instant.now();
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("field1", "error1");
        ValidateError error = new ValidateError();
        error.setTimestamp(now);
        error.setStatus(400);
        error.setError("Bad Request");
        error.setMessage("Validation error");
        error.setPath("/test");
        error.setFieldErrors(fieldErrors);

        assertEquals(now, error.getTimestamp());
        assertEquals(400, error.getStatus());
        assertEquals("Bad Request", error.getError());
        assertEquals("Validation error", error.getMessage());
        assertEquals("/test", error.getPath());
        assertEquals(fieldErrors, error.getFieldErrors());
    }
}