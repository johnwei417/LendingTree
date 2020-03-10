package com.honglin.controller.Employee;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Employee;
import com.honglin.model.entity.Roles;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.EmployeeDto;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.EmailService;
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
 * honglinwei created on 2/18/20 inside the package - com.honglin.controller.Employee
 */
@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/employee/profile")
public class EmployeeProfileController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    /**
     * get employee information by user id
     *
     * @param id which is user id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<Employee> getOne(@PathVariable int id) {
        User user = userService.findById(id);
        log.info("get employee (ID: " + id + ") info success");
        return new ApiResponse<>(HttpStatus.OK, "Customer fetched successfully.", user.getEmployees());
    }

    /**
     * edit employee profile information by userId
     *
     * @param userDto
     * @return
     */
    @PutMapping("/edit")
    public ApiResponse<Employee> edit(@RequestBody UserDto userDto) {

        User user = userService.findById(userDto.getId());
        Employee employee = user.getEmployees();

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getFirstname() != null) {
            user.setFirstname(userDto.getFirstname());
        }

        if (userDto.getLastname() != null) {
            user.setLastname(userDto.getLastname());
        }

        if (userDto.getOldPassword() != null || userDto.getPassword() != null) {
            if (bcryptEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
                user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
            }
        }

        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }

        List<Roles> authorities = new ArrayList<>();
        authorities.add(roleService.getAuthorityById(2));

        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(user, userDto1);
        userDto1.setAuthorities(authorities);
        userService.update(userDto1);

        if (userDto.getAddress() != null) {
            employee.setAddress(userDto.getAddress());
        }
        EmployeeDto employeeDto1 = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto1);
        employeeDto1.setEmpId(employee.getId());
        employeeService.update(employeeDto1);
        log.info("employee (ID: " + employee.getId() + ") info update success");
        return new ApiResponse<>(HttpStatus.OK, "Employee update successfully.", null);

    }

}
