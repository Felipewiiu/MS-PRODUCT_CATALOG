package unit_test;

import com.product_management.MS_PRODUCT_MANAGEMENT.exceptionhandler.ControllerExeptionHandler;
import com.product_management.MS_PRODUCT_MANAGEMENT.exceptionhandler.StandardError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerExceptionHandlerTest {

    private ControllerExeptionHandler handler;
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        handler = new ControllerExeptionHandler();
        request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/test");
    }

    @Test
    public void testValidateError() {
        ValidationException ex = new ValidationException("Validation failed");
        ResponseEntity<StandardError> response = handler.validateError(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed", response.getBody().getError());
        assertEquals("Validation error", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }

    @Test
    public void testNullPointerException() {
        NullPointerException ex = new NullPointerException("Null pointer");
        ResponseEntity<StandardError> response = handler.nullPoiterExeption(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Null pointer", response.getBody().getError());
        assertEquals("Null pointer exception", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }

    @Test
    public void testEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");
        ResponseEntity<StandardError> response = handler.nullPoiterExeption(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody().getError());
        assertEquals("Entity not found", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }
}