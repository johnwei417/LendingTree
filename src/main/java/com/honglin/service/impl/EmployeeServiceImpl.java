package com.honglin.service.impl;

import com.honglin.dao.EmployeeDao;
import com.honglin.model.entity.Employee;
import com.honglin.model.pojo.EmployeeDto;
import com.honglin.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/18/20 inside the package - com.honglin.service.impl
 */

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public Employee save(EmployeeDto employee) {
        Employee newUser = new Employee();
        newUser.setDepartment(employee.getDepartment());
        newUser.setAddress(employee.getAddress());
        return employeeDao.save(newUser);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        employeeDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(int id) {
        employeeDao.deleteById(id);
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id).get();
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = findById(employeeDto.getEmpId());
        if (employee != null) {
            BeanUtils.copyProperties(employeeDto, employee);
            employeeDao.save(employee);
        }

        return employeeDto;
    }


}
