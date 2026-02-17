package com.ccsw.tutorial.loan.exceptionsLoan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Invalid date selection, return date must be posterior to loan date")
public class InvalidDateSelection extends RuntimeException {
    public InvalidDateSelection() {
        super("La fecha de devolución debe ser posterior a la de alquiler");
    }
}
