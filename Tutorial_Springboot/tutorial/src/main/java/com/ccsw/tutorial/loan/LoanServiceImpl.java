package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.loan.exceptionsLoan.ExceedMaxDuration;
import com.ccsw.tutorial.loan.exceptionsLoan.ExceedMaxLoans;
import com.ccsw.tutorial.loan.exceptionsLoan.InvalidDateSelection;
import com.ccsw.tutorial.loan.exceptionsLoan.LoanInProgress;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Adrián Cotón
 *
 * @version 1.0
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    /**
     * {@inheritDoc}
     */

    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {

        return loanRepository.searchLoans(
                dto.getGameTitle(),
                dto.getClientName(),
                dto.getDate(),
                dto.getPageable().getPageable()
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, LoanDto data) {

        //Check taht the return date is posterior to loan date
        if(data.getLoanDate().after(data.getEndDate())){
            throw new InvalidDateSelection();
        }
        //--------------------------------------*---------------------------------------------------------------------------------------
        //Check that the difference between return and loan date doesnt exceed 14 days
        long diffInMillies = Math.abs(data.getEndDate().getTime() - data.getLoanDate().getTime());
        long diffInDays = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
        if (diffInDays > 14) {
            throw new ExceedMaxDuration();
        }
        //--------------------------------------*---------------------------------------------------------------------------------------
        //Loan already in progress check

        long inProgress;

        if (id == null) {
            inProgress = loanRepository.countOverlappingByGame(
                    data.getNameGame(),
                    data.getLoanDate(),
                    data.getEndDate()
            );
        } else {
            //excluir el propio préstamo
            inProgress = loanRepository.countOverlappingByGameExclude(
                    id,
                    data.getNameGame(),
                    data.getLoanDate(),
                    data.getEndDate()
            );
        }

        if (inProgress > 0) {
            throw new LoanInProgress();
        }
        //--------------------------------------*---------------------------------------------------------------------------------------
        //Check max loans per client

        long checkMaxLoans;

        if (id == null) {
            checkMaxLoans = loanRepository.countOverlappingByClient(
                    data.getNameClient(),
                    data.getLoanDate(),
                    data.getEndDate()
            );
        } else {
            // Edición: contamos "otros" préstamos (excluyendo este)
            checkMaxLoans = loanRepository.countOverlappingByClientExclude(
                    id,
                    data.getNameClient(),
                    data.getLoanDate(),
                    data.getEndDate()
            );
        }
        if (checkMaxLoans >= 2) {
            throw new ExceedMaxLoans();
        }


        Loan loan;

        if (id == null) {
            loan = new Loan();
        } else {
            loan = this.loanRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(data, loan, "id");

        this.loanRepository.save(loan);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if(this.loanRepository.findById(id).orElse(null) == null){
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

}
