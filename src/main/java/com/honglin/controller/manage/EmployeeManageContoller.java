package com.honglin.controller.manage;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Department;
import com.honglin.model.entity.Employee;
import com.honglin.model.entity.Roles;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.EmployeeDto;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.DepartmentService;
import com.honglin.service.EmployeeService;
import com.honglin.service.RoleService;
import com.honglin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/18/20 inside the package - com.honglin.controller.manage
 */

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/admin/employees")
public class EmployeeManageContoller {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleService roleService;


    /**
     * get all employees
     *
     * @return
     */
    @GetMapping
    public ApiResponse<List<Employee>> listUsers() {
        log.info("Retrieve list of employee successful");
        return new ApiResponse<>(HttpStatus.OK, "Employee list fetched successfully.", employeeService.findAll());
    }

    /**
     * get employee by employee id
     *
     * @param id which is employee id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<Employee> getOne(@PathVariable int id) {
        log.info("Retrieve employee (ID: " + id + ") successful");
        return new ApiResponse<>(HttpStatus.OK, "Employee fetched successfully.", employeeService.findById(id));
    }


    /**
     * Json: deptId if want change department, email, phone, firstname, lastname,
     * phone, password, address
     *
     * @param id          which is empId
     * @param employeeDto
     * @return
     */
    @PutMapping("/edit/{id}")
    public ApiResponse<Employee> edit(@PathVariable int id, @RequestBody EmployeeDto employeeDto) {

        Employee employee = employeeService.findById(id);
        User user = employee.getUser();
        if (employeeDto.getEmail() != null) {
            user.setEmail(employeeDto.getEmail());
        }

        if (employeeDto.getFirstname() != null) {
            user.setFirstname(employeeDto.getFirstname());
        }

        if (employeeDto.getLastname() != null) {
            user.setLastname(employeeDto.getLastname());
        }

        if (employeeDto.getPassword() != null) {
            user.setPassword(bcryptEncoder.encode(employeeDto.getPassword()));
        }

        if (employeeDto.getPhone() != null) {
            user.setPhone(employeeDto.getPhone());
        }

        List<Roles> authorities = new ArrayList<>();
        authorities.add(roleService.getAuthorityById(2));

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setAuthorities(authorities);
        userService.update(userDto);

        if (employeeDto.getAddress() != null) {
            employee.setAddress(employeeDto.getAddress());
        }

        if (employeeDto.getDeptId() != null) {
            Department department = departmentService.findById(employeeDto.getDeptId());

            if (department != null) {
                employee.setDepartment(department);
            }
        }

        EmployeeDto employeeDto1 = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto1);
        employeeDto1.setEmpId(id);
        employeeService.update(employeeDto1);
        log.info("Employee (ID:" + id + ") update success");
        return new ApiResponse<>(HttpStatus.OK, "Employee update successfully.", null);

    }


    /**
     * delete employee
     *
     * @param id which is employee id
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        User user = employeeService.findById(id).getUser();
        userService.delete(user.getId());
        log.info("Employee (ID: " + id + ") deleted success");
        return new ApiResponse<>(HttpStatus.OK, "Employee deleted successfully.", null);
    }

}
