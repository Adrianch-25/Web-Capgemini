package com.ccsw.tutorial.customer;
//Java library
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//External dependencies
import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.customer.model.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@Tag(name = "Customer", description = "API of Customer")
@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "http://localhost:4200") //Security purposes

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    ModelMapper mapper;

    /**
     * Method to recover a list with of the customers
     *
     * @return {@link List} from {@link Customer}
     */
    @Operation(summary = "Find", description = "Method that returns a list of the existing Customers")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CustomerDto> findAll() {

        List<Customer> customers = this.customerService.findAll();

        return customers.stream().map(e -> mapper.map(e, CustomerDto.class)).collect(Collectors.toList());
    }

    /**
     * Method to insert or update a Customer
     *
     * @param id PK from the Customer entity
     * @param dto Data from the entity
     */
    @Operation(summary = "Insert or Update", description = "Method to update or insert a register into Customer")
    @RequestMapping(path = {"", "/{id}"}, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto){

        this.customerService.save(id, dto);
    }

    /**
     * Method to delete a Customer
     *
     * @param id PK from the Customer entity
     */
    @Operation(summary = "Delete", description = "Method to delete a Customer")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) throws Exception{

        this.customerService.delete(id);
    }

}
