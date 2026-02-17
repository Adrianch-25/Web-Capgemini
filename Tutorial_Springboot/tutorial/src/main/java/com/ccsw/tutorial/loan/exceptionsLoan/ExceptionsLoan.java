package com.ccsw.tutorial.loan.exceptionsLoan;

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
public class ExceptionsLoan {

    @ExceptionHandler(InvalidDateSelection.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(InvalidDateSelection exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 409, //El de entidad no procesable
                "error", "Conflict",
                "code", "INVALID_DATE",
                "message", exception.getMessage()
        ));
    }
    @ExceptionHandler(ExceedMaxDuration.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(ExceedMaxDuration exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 409, //El de entidad no procesable
                "error", "Conflict",
                "code", "MAX_DURATION_EXCEED",
                "message", exception.getMessage()
        ));
    }
    @ExceptionHandler(LoanInProgress.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(LoanInProgress exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 409, //El de entidad no procesable
                "error", "Conflict",
                "code", "LOAN_IN_PROGRESS",
                "message", exception.getMessage()
        ));
    }
    @ExceptionHandler(ExceedMaxLoans.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(ExceedMaxLoans exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "timestamp", OffsetDateTime.now().toString(),
                "status", 409, //El de entidad no procesable
                "error", "Conflict",
                "code", "MAX_LOANS_EXCEED",
                "message", exception.getMessage()
        ));
    }
}
