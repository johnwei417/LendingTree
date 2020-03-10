package com.honglin.service.impl;

import com.honglin.dao.DepartmentDao;
import com.honglin.model.entity.Department;
import com.honglin.model.pojo.DepartmentDto;
import com.honglin.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/20/20 inside the package - com.honglin.service.impl
 */
@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        departmentDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Department findById(Integer id) {
        return departmentDao.findById(id).get();
    }

    @Override
    public Department findByDeptName(String deptName) {
        return departmentDao.findByDeptName(deptName);
    }

    @Override
    public Department save(DepartmentDto departmentDto) {
        Department newDepartment = new Department();
        newDepartment.setDeptName(departmentDto.getDeptName());
        return departmentDao.save(newDepartment);
    }

    @Override
    public void delete(int deptId) {
        //admin department can never be deleted
        if (deptId != 1) {
            departmentDao.deleteById(deptId);

        }
    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        //cannot modify admin dept
        if (departmentDto.getId() != 1) {
            Department department = findById(departmentDto.getId());
            if (department != null) {
                BeanUtils.copyProperties(departmentDto, department);

                departmentDao.save(department);
            }
        }
        return departmentDto;
    }

    @Override
    public DepartmentDto updateDepartmentName(DepartmentDto departmentDto) {
        Department department = findById(departmentDto.getId());
        if (department != null) {
            BeanUtils.copyProperties(departmentDto, department);
            departmentDao.save(department);
        }
        return departmentDto;
    }

    @Override
    public Long countRows() {
        return departmentDao.count();
    }

}
