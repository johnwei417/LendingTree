package com.honglin.service;


import com.honglin.model.entity.Department;
import com.honglin.model.pojo.DepartmentDto;

import java.util.List;

/**
 * honglinwei created on 2/20/20 inside the package - com.honglin.service
 */
public interface DepartmentService {

    List<Department> findAll();

    Department findById(Integer id);

    Department findByDeptName(String deptName);

    Department save(DepartmentDto departmentDto);

    void delete(int deptId);

    DepartmentDto update(DepartmentDto departmentDto);

    DepartmentDto updateDepartmentName(DepartmentDto departmentDto);

    Long countRows();
}
