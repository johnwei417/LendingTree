package com.honglin.model.pojo;

import com.honglin.model.entity.*;

/**
 * honglinwei created on 2/20/20 inside the package - com.honglin.model.pojo
 */
public class LoanDto {

    private Integer id;
    private Integer userId;
    private Integer cusId;
    private double amount;
    private Department department;
    private Integer deptId;
    private Bank banks;
    private Customer customer;
    private LoanType loanType;
    private Integer bankId;
    private Integer loantypeId;
    private LoanStatus loanStatus;
    private Integer loanstatusId;

    public Integer getLoanstatusId() {
        return loanstatusId;
    }

    public void setLoanstatusId(Integer loanstatusId) {
        this.loanstatusId = loanstatusId;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLoantypeId() {
        return loantypeId;
    }

    public void setLoantypeId(Integer loantypeId) {
        this.loantypeId = loantypeId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departments) {
        this.department = departments;
    }

    public Bank getBanks() {
        return banks;
    }

    public void setBanks(Bank banks) {
        this.banks = banks;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}
