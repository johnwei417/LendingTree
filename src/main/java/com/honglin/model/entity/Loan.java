package com.honglin.model.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;


/**
 * honglinwei created on 2/20/20 inside the package - com.honglin.model.entity
 */

@Entity
@Table(name = "loan")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer id;

    @Column
    private double amount;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "loans_departments", joinColumns = @JoinColumn(name = "loan_id", referencedColumnName = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "dept_id", referencedColumnName = "dept_id"))
    @JsonIgnore
    private Department department;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "loans_customers", joinColumns = @JoinColumn(name = "loan_id", referencedColumnName = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "cus_id", referencedColumnName = "cus_id"))
    @JsonIgnore
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "loans_banks", joinColumns = @JoinColumn(name = "loan_id", referencedColumnName = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "bank_id"))
    @JsonIgnore
    private Bank banks;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "loans_loantype", joinColumns = @JoinColumn(name = "loan_id", referencedColumnName = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "loantype_id", referencedColumnName = "loantype_id"))
    @JsonIgnore
    private LoanType loanType;

    //TODO: adding loan status later, if status is approve, then auto update department (calling setDepartment()method
    //in the frontend, when user click "approve", call api called "verifyStatus" which should update department automatically

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "loans_loanstatus", joinColumns = @JoinColumn(name = "loan_id", referencedColumnName = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "loanstatus_id", referencedColumnName = "loanstatus_id"))
    @JsonIgnore
    private LoanStatus loanStatus;

    /**
     * info return into json
     *
     * @return
     */
    public String getLoanTypeName() {
        return this.getLoanType().getLoanType();
    }


    public String getUsername() {
        return this.getCustomer().getUser().getUsername();
    }


    public String getBankName() {
        return this.getBanks().getBankName();
    }

    public String getDepartmentName() {
        return this.getDepartment().getDeptName();
    }

    public String getLoanStatusName() {
        return this.getLoanStatus().getStatusName();
    }


//  / public Integer getDeptId(){
//        return this.getDepartment().getId();
//    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
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

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Bank getBanks() {
        return banks;
    }

    public void setBanks(Bank banks) {
        this.banks = banks;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }
}
