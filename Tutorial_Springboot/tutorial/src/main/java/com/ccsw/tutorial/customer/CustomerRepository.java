package com.ccsw.tutorial.customer;

import com.ccsw.tutorial.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 *
 * Aprovechamos la integración de CrudRepository para filtrar por nombre o ID's
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
}
