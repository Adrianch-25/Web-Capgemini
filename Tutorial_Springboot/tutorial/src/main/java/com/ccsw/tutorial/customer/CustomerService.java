package com.ccsw.tutorial.customer;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;

import java.util.List;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
public interface CustomerService {

    /**
     * Method to recover a list with of the customers
     *
     * @return {@link List} from {@link Customer}
     */
     List<Customer> findAll();

    /**
     * Method to insert or update a Customer
     *
     * @param id PK from the Customer entity
     * @param dto Data from the entity
     */
    void save(Long id, CustomerDto dto);

    /**
     * Method to delete a Customer
     *
     * @param id PK from the Customer entity
     */
    void delete(Long id) throws Exception;
}
