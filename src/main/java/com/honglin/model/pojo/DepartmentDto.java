package com.honglin.model.pojo;

import com.honglin.model.entity.Employee;
import com.honglin.model.entity.Loan;

import java.util.Collection;
import java.util.List;

/**
 * honglinwei created on 2/20/20 inside the package - com.honglin.model.pojo
 */
public class DepartmentDto {

    private Integer id;
    private String deptName;
    private Collection<Employee> employees;
    private List<Loan> loans;


    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


}
