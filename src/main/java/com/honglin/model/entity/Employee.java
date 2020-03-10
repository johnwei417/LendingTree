package com.honglin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * honglinwei created on 2/19/20 inside the package - com.honglin.model.entity
 */

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer id;

    @Column(name = "address")
    private String address;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "employees_departments", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "dept_id", referencedColumnName = "dept_id"))
    @JsonIgnore
    private Department department;

    @OneToOne(mappedBy = "employees")
    @JsonIgnore
    private User user;

    public String getDeptName() {
        return this.getDepartment().getDeptName();
    }

    public String getFirstname() {
        return this.getUser().getFirstname();
    }

    public String getLastname() {
        return this.getUser().getLastname();
    }

    public String getEmail() {
        return this.getUser().getEmail();
    }

    public String getPhone() {
        return this.getUser().getPhone();
    }

    public Integer getDeptId() {
        return this.getDepartment().getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
