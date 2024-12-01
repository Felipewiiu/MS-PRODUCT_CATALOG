package com.product_management.MS_PRODUCT_MANAGEMENT.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExeptionHandler {



    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validateError(ValidationException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(ex.getMessage());
        error.setMessage("Validation error");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> nullPoiterExeption(NullPointerException ex, HttpServletRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus.value());
        error.setError(ex.getMessage());
        error.setMessage("Null pointer exception");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(error);
    }



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> nullPoiterExeption(EntityNotFoundException ex, HttpServletRequest request) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError();

        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus.value());
        error.setError(ex.getMessage());
        error.setMessage("Entity not found");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(httpStatus).body(error);
    }

}
