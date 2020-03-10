package com.honglin.controller.Customer;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Customer;
import com.honglin.model.entity.Roles;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.CustomerDto;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.CustomerService;
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
 * honglinwei created on 2/23/20 inside the package - com.honglin.controller.Customer
 */

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/customer/profile")
public class CustomerProfileController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerService customerService;

    /**
     * get customer information by user id
     *
     * @param id which is user id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<Customer> getOne(@PathVariable int id) {
        User user = userService.findById(id);
        log.info("Customer (ID: " + id + ") info retrieved success");
        return new ApiResponse<>(HttpStatus.OK, "Customer fetched successfully.", user.getCustomer());
    }

    /**
     * edit customer profile information
     *
     * @param userDto which is user pojo
     * @return
     */
    @PutMapping("/edit")
    public ApiResponse<Customer> edit(@RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        Customer customer = user.getCustomer();

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
        //role is customer;
        authorities.add(roleService.getAuthorityById(3));

        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(user, userDto1);
        userDto1.setAuthorities(authorities);
        userService.update(userDto1);

        CustomerDto customerDto1 = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto1);
        if (userDto.getSalary() != null) {
            customerDto1.setSalary(userDto.getSalary());
        }
        customerService.update(customerDto1);
        log.info("Customer (ID: " + customer.getId() + ") info update successfully");
        return new ApiResponse<>(HttpStatus.OK, "Customer update successfully.", null);
    }
}
