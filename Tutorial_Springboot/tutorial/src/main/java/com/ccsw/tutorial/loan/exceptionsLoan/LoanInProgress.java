package com.ccsw.tutorial.loan.exceptionsLoan;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The game is already given to another customer")
public class LoanInProgress extends RuntimeException {
    public LoanInProgress() {
        super("El juego ya tiene un alquiler en curso");
    }
}
