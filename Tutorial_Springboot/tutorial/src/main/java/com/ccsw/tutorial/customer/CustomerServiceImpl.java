package com.ccsw.tutorial.customer;

import com.ccsw.tutorial.customer.exceptionsCustomer.DuplicateCustomerException;
import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * {@InheritDoc}
     */
    @Override
    public List<Customer> findAll(){

        return (List<Customer>) this.customerRepository.findAll();
    }

    /**
     * {@InheritDoc}
     */
    @Override
    public void save(Long id, CustomerDto dto){
        //Save name eliminating side blanks
        String name = dto.getName().trim();

        if(id == null) {
            //For checking duplicates while creating
            if (customerRepository.existsByNameIgnoreCase(name)) {
                throw new DuplicateCustomerException(name);
            }
        }
        //For excluding the selected customer while updating it
        else{
            if(customerRepository.existsByNameIgnoreCaseAndIdNot(name, id)){
                throw new DuplicateCustomerException(name);
            }
        }
        Customer customer;

        if(id == null){
            customer = new Customer();
        }
        else{
            customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("Customer not found")));
        }

        customer.setName(name);

        customerRepository.save(customer);
    }

    /**
     * {@InheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception{

        if(this.customerRepository.findById(id).orElse(null) == null){
            throw new Exception("Not exists");
        }

        this.customerRepository.deleteById(id);
    }

}
