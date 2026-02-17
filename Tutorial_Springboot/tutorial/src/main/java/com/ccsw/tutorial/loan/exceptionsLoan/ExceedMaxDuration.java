package com.ccsw.tutorial.loan.exceptionsLoan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Invalid date selection, return date must be posterior to loan date")
public class ExceedMaxDuration extends RuntimeException {
    public ExceedMaxDuration() {
        super("La duración del alquiler no puede ser mayor a 14 días");
    }
}
