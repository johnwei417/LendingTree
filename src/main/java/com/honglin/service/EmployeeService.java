package com.honglin.service;

import com.honglin.model.entity.Employee;
import com.honglin.model.pojo.EmployeeDto;

import java.util.List;

public interface EmployeeService {


    Employee save(EmployeeDto employeeDto);

    List<Employee> findAll();

    void delete(int id);

    Employee findById(int id);

    EmployeeDto update(EmployeeDto employeeDto);


}
