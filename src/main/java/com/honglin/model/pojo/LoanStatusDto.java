package com.honglin.model.pojo;

import com.honglin.model.entity.Loan;

import java.util.List;

/**
 * honglinwei created on 2/23/20 inside the package - com.honglin.model.pojo
 */
public class LoanStatusDto {
    private Integer id;
    private String statusName;
    private List<Loan> loans;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
