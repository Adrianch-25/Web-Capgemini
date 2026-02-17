package com.ccsw.tutorial.loan;


import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ccsw
 *
 */
public interface LoanService {

    /**
     * Método para recuperar un listado paginado de {@link Loan}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Loan}
     */
    Page<Loan> findPage(LoanSearchDto dto);

    /**
     * Method to create or update a {@link Loan}
     *
     * @param id PK from Loan Entity
     * @param dto data from Loan entity
     */
    void save(Long id, LoanDto dto);

    /**
     * Method to delete a Loan {@link Loan}
     *
     * @param id PK from Loan entity
     */
    void delete(Long id) throws Exception;

}
