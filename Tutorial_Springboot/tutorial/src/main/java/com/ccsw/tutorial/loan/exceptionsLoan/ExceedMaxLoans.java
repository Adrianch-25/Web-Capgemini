package com.ccsw.tutorial.loan.exceptionsLoan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The customer exceed max loans (2)")
public class ExceedMaxLoans extends RuntimeException {
    public ExceedMaxLoans() {
        super("El cliente ya tiene 2 préstamos en curso");
    }
}
