package com.honglin.controller;

import com.honglin.common.ApiResponse;
import com.honglin.common.AuthToken;
import com.honglin.config.JwtTokenUtil;
import com.honglin.model.entity.Customer;
import com.honglin.model.entity.Employee;
import com.honglin.model.entity.Roles;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.CustomerDto;
import com.honglin.model.pojo.EmployeeDto;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/loan")
@Slf4j
@Api(value = "User Authentication API")
public class UserAuthenticationController {
    private static final int ROLE_USER_AUTHORITY_ID = 3;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DepartmentService departmentService;


    @Autowired
    private RoleService roleService;

    @Autowired
    private EmailService emailService;


    /**
     * Login function
     * once login success, it should return jwt token, user id, username as well
     * it will also come with http status and message
     *
     * @param loginUser
     * @return token, id, username, status, msg
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "user login", notes = "login with username and password")
    public ApiResponse<AuthToken> login(@RequestBody UserDto loginUser) throws AuthenticationException {
        log.trace(loginUser.getUsername() + " start login");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        if (user == null) {
            log.error(loginUser.getUser() + " is not exist");
        }
        final String token = jwtTokenUtil.generateToken(user);
        if (token == null) {
            log.error("token generation failed");
        }
        log.info(user.getUsername() + " login successfully");
        return new ApiResponse<>(HttpStatus.OK, "success", new AuthToken(token, user.getUsername(), user.getId(), user.getRoles()));
    }


    /**
     * Register function
     *
     * @param user
     * @return status, msg
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponse<AuthToken> saveUser(@RequestBody UserDto user) throws Exception {
        try {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null ||
                    user.getPhone() == null || user.getSalary() == null) {
                return new ApiResponse<>(HttpStatus.NOT_ACCEPTABLE, "", null);
            }
            if (userService.findOne(user.getUsername()) != null) {
                return new ApiResponse<>(HttpStatus.CONFLICT, "Duplicate Username", null);
            }

            List<Roles> authorities = new ArrayList<>();
            authorities.add(roleService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
            user.setAuthorities(authorities);

            CustomerDto customerDto = new CustomerDto();
            customerDto.setSalary(user.getSalary());

            Customer cus = customerService.save(customerDto);

            user.setCustomer(cus);

            userService.save(user);

            //if no errors or exceptions, sent confirmation email to user's email address
            emailService.sendEmail(user.getEmail(), "Confirmation email for registration", "Thanks you for register our lending loan website!");
            return new ApiResponse<>(HttpStatus.OK, "Success", null);

        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            log.error(user.getUsername() + " failed to register");
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Duplicate Username", null);
        }
    }

    /**
     * JSON: deptId and address is needed for employee
     *
     * @param user
     * @return
     */

    @RequestMapping(value = "/registerEmployee", method = RequestMethod.POST)
    public ApiResponse<AuthToken> addEmployee(@RequestBody UserDto user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null ||
                    user.getPhone() == null || user.getAddress() == null || user.getDeptId() == null) {
                return new ApiResponse<>(HttpStatus.NOT_ACCEPTABLE, "", null);
            }
            if (userService.findOne(user.getUsername()) != null) {
                return new ApiResponse<>(HttpStatus.CONFLICT, "Duplicate Username", null);
            }
            //role
            List<Roles> authorities = new ArrayList<>();
            // if not admin department, then role is 2 which is employee
            if (user.getDeptId() != 1) {
                authorities.add(roleService.getAuthorityById(2));
                user.setAuthorities(authorities);

                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setAddress(user.getAddress());
                employeeDto.setDepartment(departmentService.findById(user.getDeptId()));

                Employee emp = employeeService.save(employeeDto);

                user.setEmployees(emp);
                userService.save(user);
            } else {
                authorities.add(roleService.getAuthorityById(1));
                user.setAuthorities(authorities);
                userService.save(user);
            }

            //if no errors or exceptions, sent confirmation email to user's email address
            emailService.sendEmail(user.getEmail(), "Confirmation email for registration", "Thanks you for register our lending loan website!");
            return new ApiResponse<>(HttpStatus.OK, "Success", null);


        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            log.error(user.getUsername() + " failed to register");
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Duplicate Username", null);
        }
    }

    @RequestMapping(value = "/registerAdmin", method = RequestMethod.POST)
    public ApiResponse<AuthToken> addAdmin(@RequestBody UserDto user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null ||
                    user.getPhone() == null) {
                return new ApiResponse<>(HttpStatus.NOT_ACCEPTABLE, "", null);
            }
            if (userService.findOne(user.getUsername()) != null) {
                return new ApiResponse<>(HttpStatus.CONFLICT, "Duplicate Username", null);
            }

            List<Roles> authorities = new ArrayList<>();
            authorities.add(roleService.getAuthorityById(1));
            user.setAuthorities(authorities);
            userService.save(user);

            //if no errors or exceptions, sent confirmation email to user's email address
            emailService.sendEmail(user.getEmail(), "Confirmation email for registration", "Thanks you for register our lending loan website!");
            return new ApiResponse<>(HttpStatus.OK, "Success", null);

        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Duplicate Username", null);
        }
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ApiResponse<AuthToken> processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request) {

        // Lookup user in database by e-mail
        User user = userService.findUserByEmail(userEmail);

        if (user == null) {

        } else {

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);

            userService.updateResetToken(userDto);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080/api/v1/loan";

            // Email message
            emailService.sendEmail(user.getEmail(), "Password Reset Request", "To reset your password, click the link below:" + appUrl
                    + "/reset?token=" + user.getResetToken());

        }
        return new ApiResponse<>(HttpStatus.OK, "Success", new AuthToken(user.getResetToken(), user.getUsername(), user.getId(), user.getRoles()));

    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ApiResponse<AuthToken> setNewPassword(@RequestParam Map<String, String> requestParams) {

        // Find the user associated with the reset token
        User user = userService.findUserByResetToken(requestParams.get("token"));

        // This should always be non-null but we check just in case
        if (user != null) {
            user.setPassword(requestParams.get("password"));
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userService.resetPassword(userDto);

        } else {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Failed", null);
        }
        return new ApiResponse<>(HttpStatus.OK, "Success Reset Password", null);

    }


}
