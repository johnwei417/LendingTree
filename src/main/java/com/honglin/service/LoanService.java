package com.honglin.service;

import com.honglin.model.entity.Loan;
import com.honglin.model.pojo.LoanDto;

import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.service
 */
public interface LoanService {
    List<Loan> findAll();

    Loan save(LoanDto loanDto);

    void delete(int loanId);

    Loan findById(int loanId);

    LoanDto update(LoanDto loanDto);

    List<Loan> findAllActive();

    List<Loan> findAllPending();

    List<Loan> findAllRejected();

    LoanDto changeDepartment(LoanDto loanDto);

    LoanDto changeLoanStatus(LoanDto loanDto);
}
