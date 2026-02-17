package com.ccsw.tutorial.customer.exceptionsCustomer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionsCustomer {

    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateCustomerException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 409, //El de recurso duplicado
                "error", "Conflict",
                "code", "CUSTOMER_DUPLICATE",
                "message", exception.getMessage()
        ));
    }
}
