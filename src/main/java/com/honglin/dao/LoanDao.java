package com.honglin.dao;


import com.honglin.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * honglinwei created on 2/21/20 inside the package - com.honglin.dao
 */
public interface LoanDao extends JpaRepository<Loan, Integer> {

    @Query("SELECT l FROM Loan l WHERE l.loanStatus.statusName = 'APPROVED'")
    List<Loan> findAllActiveLoans();

    @Query("SELECT l FROM Loan l WHERE l.loanStatus.statusName = 'PENDING'")
    List<Loan> findAllPendingLoans();

    @Query("SELECT l FROM Loan l WHERE l.loanStatus.statusName = 'REJECTED'")
    List<Loan> findAllRejectedLoans();


}
