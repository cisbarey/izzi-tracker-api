package com.izzi.iptracker.exception;

import com.izzi.iptracker.dto.response.ErrorResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateKeyException(DuplicateKeyException ex) {
        return this.build("The IP address already exists in the database.", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IPAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleIPAlreadyExistsException(IPAlreadyExistsException ex) {
        return this.build(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IPNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIPNotFoundException(IPNotFoundException ex) {
        return this.build(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        return this.build("Error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> build(String message, HttpStatus status) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(message)
                .status(status.value())
                .timestamp(System.currentTimeMillis())
                .build(), status);
    }
}
