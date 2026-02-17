package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.LoanServiceImpl;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@Tag(name = "Loan", description = "API of Loan")
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    ModelMapper mapper;

    /**
     * Method to recover a list of {@link Loan}
     *
     * @param dto dto to search
     * @return {@link Page} de {@link LoanDto}
     */
    @Operation(summary = "Find Page", description = "Method that return a page of Loans")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<LoanDto> findPage(@RequestBody LoanSearchDto dto) {

        Page<Loan> page = this.loanService.findPage(dto);

        return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    /**
     * Method to create or update Loans{@link Loan}
     *
     * @param id PK from Loan entity
     * @param dto data from Loan entity
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Loab")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody LoanDto dto) {
        this.loanService.save(id, dto);
    }

    /**
     * Method to delete a Loan {@link Loan}
     *
     * @param id PK from loan entity
     */
    @Operation(summary = "Delete", description = "Method that deletes a Loan")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.loanService.delete(id);
    }

}