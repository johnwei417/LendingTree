package com.honglin.model.pojo;

import com.honglin.model.entity.Loan;
import com.honglin.model.entity.LoanType;

import java.util.List;

/**
 * honglinwei created on 2/18/20 inside the package - com.honglin.model.pojo
 */
public class BankDto {

    private Integer bankId;

    private String bankName;

    private List<Loan> loans;

    private List<LoanType> loanTypes;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<LoanType> getLoanTypes() {
        return loanTypes;
    }

    public void setLoanTypes(List<LoanType> loanTypes) {
        this.loanTypes = loanTypes;
    }
}
