package com.ccsw.tutorial.customer.exceptionsCustomer;
/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
public class DuplicateCustomerException extends RuntimeException{

    public DuplicateCustomerException(String name){

        super("Customer with name: " + name + " already exists");
    }
}


