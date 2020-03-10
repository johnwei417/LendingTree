package com.honglin.model.pojo;

import com.honglin.model.entity.Bank;

import java.util.List;

/**
 * honglinwei created on 2/21/20 inside the package - com.honglin.model.pojo
 */
public class LoanTypeDto {

    private Integer loanTypeId;

    private String loanType;

    private List<Bank> banks;

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
