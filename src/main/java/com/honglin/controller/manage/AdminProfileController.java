package com.honglin.controller.manage;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Roles;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.UserDto;
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
 * honglinwei created on 3/2/20 inside the package - com.honglin.controller.manage
 */
@CrossOrigin(maxAge = 3600)
@Slf4j
@RestController
@RequestMapping("/api/v1/loan/admin/profile")
public class AdminProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleService roleService;

    /**
     * edit user information
     *
     * @param userDto
     * @return
     */
    @PutMapping("/edit")
    public ApiResponse<User> edit(@RequestBody UserDto userDto) {

        User user = userService.findById(userDto.getId());

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getFirstname() != null) {
            user.setFirstname(userDto.getFirstname());
        }

        if (userDto.getLastname() != null) {
            user.setLastname(userDto.getLastname());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        }

        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }

        List<Roles> authorities = new ArrayList<>();
        //role is customer;
        authorities.add(roleService.getAuthorityById(1));

        UserDto userDto1 = new UserDto();
        BeanUtils.copyProperties(user, userDto1);
        userDto1.setAuthorities(authorities);
        userService.update(userDto1);
        log.info(userDto.getUsername() + " edit profile successful");
        return new ApiResponse<>(HttpStatus.OK, "Admin Information update successfully.", null);

    }

    /**
     * get admin information/profile
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id) {
        log.info("retrieve user information success");
        return new ApiResponse<>(HttpStatus.OK, "Admin fetched successfully.", userService.findById(id));
    }


}
