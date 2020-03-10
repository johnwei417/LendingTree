package com.honglin.controller.manage;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Customer;
import com.honglin.model.entity.Loan;
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
 * honglinwei created on 2/23/20 inside the package - com.honglin.controller.manage
 */

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/admin/customers")

public class CustomerManageController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleService roleService;

    /**
     * get all customers
     *
     * @return list of customers
     */
    @GetMapping
    public ApiResponse<List<Customer>> listCustomers() {
        log.info("get customer list success");
        return new ApiResponse<>(HttpStatus.OK, "Customer list fetched successfully.", customerService.findAll());
    }

    /**
     * get customer information
     *
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public ApiResponse<Customer> getOne(@PathVariable int id) {
        return new ApiResponse<>(HttpStatus.OK, "Customer fetched successfully.", customerService.findById(id));
    }

    /**
     * get customers' loan list
     *
     * @param id which is customer id
     * @return
     */
    @GetMapping("/{id}/loans")
    public ApiResponse<List<Loan>> getOneLoanDetails(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        List<Loan> loans = customer.getLoans();
        if (loans == null) {
            log.error(customer.getUser().getUsername() + " get loan list failed ");
        }
        return new ApiResponse<>(HttpStatus.OK, "Customer fetched successfully.", loans);
    }


    /**
     * edit customer information
     * JSON: email, firstname, lastname, password, phone
     *
     * @param id          which is customer id
     * @param customerDto
     * @return
     */
    @PutMapping("/edit/{id}")
    public ApiResponse<Customer> edit(@PathVariable int id, @RequestBody CustomerDto customerDto) {

        Customer customer = customerService.findById(id);

        User user = customer.getUser();
        if (user == null) {
            log.error("failed to get customer user information for customer id =" + customer.getId());
        }
        if (customerDto.getEmail() != null) {
            user.setEmail(customerDto.getEmail());
        }

        if (customerDto.getFirstname() != null) {
            user.setFirstname(customerDto.getFirstname());
        }

        if (customerDto.getLastname() != null) {
            user.setLastname(customerDto.getLastname());
        }

        if (customerDto.getPassword() != null) {
            user.setPassword(bcryptEncoder.encode(customerDto.getPassword()));
        }

        if (customerDto.getPhone() != null) {
            user.setPhone(customerDto.getPhone());
        }

        List<Roles> authorities = new ArrayList<>();
        //role is customer;
        authorities.add(roleService.getAuthorityById(3));

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setAuthorities(authorities);
        userService.update(userDto);

        CustomerDto customerDto1 = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto1);
        if (customerDto.getSalary() != null) {
            customerDto1.setSalary(customerDto.getSalary());
        }
        customerService.update(customerDto1);

        log.info(customerDto1.getFirstname() + " update info success");

        return new ApiResponse<>(HttpStatus.OK, "Customer update successfully.", null);

    }


    /**
     * delete a customer from databases
     *
     * @param id which is customer id here
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        User user = customerService.findById(id).getUser();
        userService.delete(user.getId());
        log.info("Customer ID: " + id + "deleted success");
        return new ApiResponse<>(HttpStatus.OK, "Customer deleted successfully.", null);
    }

}
