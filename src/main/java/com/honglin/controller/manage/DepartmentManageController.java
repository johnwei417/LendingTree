package com.honglin.controller.manage;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Department;
import com.honglin.model.entity.Employee;
import com.honglin.model.pojo.DepartmentDto;
import com.honglin.service.DepartmentService;
import com.honglin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * honglinwei created on 3/1/20 inside the package - com.honglin.controller.manage
 */


@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/admin/departments")
public class DepartmentManageController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * get list of departments from database;
     *
     * @return list of departments
     */
    @GetMapping("/list")
    public ApiResponse<List<Department>> listDepts() {
        List<Department> list = departmentService.findAll();
        log.info("department list retrieved success");
        return new ApiResponse<>(HttpStatus.OK, "Department list fetched successfully.", list);
    }

    /**
     * get department info by department id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<Department> getOne(@PathVariable int id) {
        log.info("Get department " + id + " success");
        return new ApiResponse<>(HttpStatus.OK, "Department info fetched successfully.", departmentService.findById(id));
    }


    /**
     * delete one department by department id
     *
     * @param id department id
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        List<Employee> employeeList = departmentService.findById(id).getEmployees();
        for (Employee employee : employeeList) {
            employeeService.delete(employee.getId());
            System.out.println(employee.getId());
        }

        departmentService.delete(id);

        return new ApiResponse<>(HttpStatus.OK, "Department deleted successfully.", null);
    }

    /**
     * @param id            which is department id;
     * @param departmentDto
     * @return
     */
    @PutMapping("/edit/{id}")
    public ApiResponse<Employee> edit(@PathVariable int id, @RequestBody DepartmentDto departmentDto) {

        Department department = departmentService.findById(id);
        if (departmentDto.getDeptName() != null) {
            department.setDeptName(departmentDto.getDeptName());
        }

        DepartmentDto departmentDto1 = new DepartmentDto();
        BeanUtils.copyProperties(department, departmentDto1);
        departmentService.update(departmentDto1);
        log.info("Edit Department （ID: " + id + "） success");
        return new ApiResponse<>(HttpStatus.OK, "Department update successfully.", null);
    }

    /**
     * add new department
     *
     * @param departmentDto
     * @return
     */
    @PostMapping("/addNew")
    public ApiResponse<Department> addNewDepartment(@RequestBody DepartmentDto departmentDto) {
        log.info(departmentDto.getDeptName() + " department added success");
        return new ApiResponse<>(HttpStatus.OK, "Department added successfully.", departmentService.save(departmentDto));
    }


}
