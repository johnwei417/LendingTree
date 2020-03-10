package com.honglin.service.impl;

import com.honglin.dao.LoanDao;
import com.honglin.model.entity.Loan;
import com.honglin.model.pojo.LoanDto;
import com.honglin.service.LoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.service.impl
 */

@Service(value = "loanService")
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Override
    public List<Loan> findAll() {

        return loanDao.findAll();
    }

    @Override
    public Loan save(LoanDto loanDto) {
        Loan loan = new Loan();

        loan.setAmount(loanDto.getAmount());
        loan.setCustomer(loanDto.getCustomer());
        loan.setDepartment(loanDto.getDepartment());
        loan.setBanks(loanDto.getBanks());
        loan.setLoanType(loanDto.getLoanType());
        loan.setLoanStatus(loanDto.getLoanStatus());

        return loanDao.save(loan);
    }

    @Override
    public void delete(int loanId) {
        loanDao.deleteById(loanId);
    }

    @Override
    public Loan findById(int loanId) {
        return loanDao.findById(loanId).get();
    }

    @Override
    public LoanDto update(LoanDto loanDto) {

        Loan loan = findById(loanDto.getId());
        if (loan != null) {
            BeanUtils.copyProperties(loanDto, loan);
            loanDao.save(loan);
        }
        return loanDto;
    }

    @Override
    public List<Loan> findAllActive() {
        return loanDao.findAllActiveLoans();
    }

    @Override
    public List<Loan> findAllPending() {
        return loanDao.findAllPendingLoans();
    }

    @Override
    public List<Loan> findAllRejected() {
        return loanDao.findAllRejectedLoans();
    }

    @Override
    public LoanDto changeDepartment(LoanDto loanDto) {
        Loan loan = findById(loanDto.getId());
        if (loan != null) {
            //loan.setDepartment(loanDto.getDepartment());
            BeanUtils.copyProperties(loanDto, loan, "id", "amount", "customer", "banks", "loanType", "loanStatus");
            loanDao.save(loan);
        }

        return loanDto;
    }

    @Override
    public LoanDto changeLoanStatus(LoanDto loanDto) {
        Loan loan = findById(loanDto.getId());
        if (loan != null) {
            BeanUtils.copyProperties(loanDto, loan, "id", "amount", "customer", "banks", "loanType", "department");
            loanDao.save(loan);
        }
        return loanDto;
    }
}
