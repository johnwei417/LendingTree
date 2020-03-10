package com.honglin.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * honglinwei created on 2/21/20 inside the package - com.honglin.model.entity
 */

@Entity
@Table(name = "loantype")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loantype_id")
    private Integer id;

    @Column(name = "loantype")
    private String loanType;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "loantypes_banks", joinColumns = @JoinColumn(name = "loantype_id", referencedColumnName = "loantype_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "bank_id"))
    private List<Bank> banks;

    @OneToMany(mappedBy = "loanType")
    private List<Loan> loan;


    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
}
