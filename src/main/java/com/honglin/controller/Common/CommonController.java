package com.honglin.controller.Common;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Bank;
import com.honglin.model.entity.Department;
import com.honglin.model.entity.LoanType;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.BankService;
import com.honglin.service.DepartmentService;
import com.honglin.service.LoanTypeService;
import com.honglin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * honglinwei created on 2/24/20 inside the package - com.honglin.controller.Common
 */

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/loan/common")
public class CommonController {

    @Autowired
    private BankService bankService;

    @Autowired
    private LoanTypeService loanTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/listBanks")
    public ApiResponse<List<Bank>> listBanks() {
        List<Bank> list = bankService.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Bank list fetched successfully.", list);
    }

    @GetMapping("/listLoanTypes")
    public ApiResponse<List<LoanType>> listLoanTypes() {
        List<LoanType> list = loanTypeService.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Loan Type list fetched successfully.", list);
    }

    @GetMapping("/listDepts")
    public ApiResponse<List<Department>> listDepts() {
        List<Department> list = departmentService.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Department list fetched successfully.", list);
    }

    @GetMapping("/checkValidforLogin")
    public Boolean checkValidforLogin(@RequestBody UserDto userDto) {

        //if username is not exist in the db
        //which means it is not valid for login
        //System.out.println(userDto.getUsername());
        return userService.findOne(userDto.getUsername()) != null;
    }

    @GetMapping("/checkValidforRegister")
    public Boolean checkValidforRegister(@RequestBody UserDto userDto) {
        User user = userService.findOne(userDto.getUsername());
        //if username already exist in the db,
        // it is not valid for register
        return user == null;
    }

}
