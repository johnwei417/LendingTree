package com.honglin.dao;

import com.honglin.model.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Integer> {

    Department findByDeptName(String deptName);
}
