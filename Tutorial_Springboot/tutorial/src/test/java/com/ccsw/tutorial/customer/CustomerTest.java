package com.ccsw.tutorial.customer;
import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 *
 * Pruebas unitarias Customer
 */
@ExtendWith(MockitoExtension.class)
public class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl categoryService;

    @Test
    public void findAllShouldReturnAllCustomers() {

        List<Customer> list = new ArrayList<>();
        list.add(mock(Customer.class));

        when(customerRepository.findAll()).thenReturn(list);

        List<Customer> customer = categoryService.findAll();

        assertNotNull(customer);
        assertEquals(1, customer.size());
    }
    public static final String CATEGORY_NAME = "CAT1";

    @Test
    public void saveNotExistsCustomerIdShouldInsert() {

        CustomerDto categoryDto = new CustomerDto();
        categoryDto.setName(CATEGORY_NAME);

        ArgumentCaptor<Customer> category = ArgumentCaptor.forClass(Customer.class);

        categoryService.save(null, categoryDto);

        verify(customerRepository).save(category.capture());

        assertEquals(CATEGORY_NAME, category.getValue().getName());
    }
    public static final Long EXISTS_CATEGORY_ID = 1L;

    @Test
    public void saveExistsCustomerIdShouldUpdate() {

        CustomerDto categoryDto = new CustomerDto();
        categoryDto.setName(CATEGORY_NAME);

        Customer category = mock(Customer.class);
        when(customerRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        categoryService.save(EXISTS_CATEGORY_ID, categoryDto);

        verify(customerRepository).save(category);
    }

}
