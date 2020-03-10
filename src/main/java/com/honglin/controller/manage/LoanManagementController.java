package com.honglin.controller.manage;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Loan;
import com.honglin.model.pojo.LoanDto;
import com.honglin.service.DepartmentService;
import com.honglin.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.controller.manage
 */
@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/admin/loanviewer")
public class LoanManagementController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private DepartmentService departmentService;


    /**
     * get list of all loans
     *
     * @return
     */
    @GetMapping
    public ApiResponse<List<Loan>> listLoans() {
        log.info("Retrieve list of loans success");
        List<Loan> list = loanService.findAll();
        return new ApiResponse<>(HttpStatus.OK, "Loan list fetched successfully.", list);
    }


    /**
     * return list of active loans
     *
     * @return
     */
    @GetMapping("/active")
    public ApiResponse<List<Loan>> listActiveLoans() {
        log.info("Retrieve approved loan list success");
        List<Loan> list = loanService.findAllActive();
        return new ApiResponse<>(HttpStatus.OK, "Active Loan list fetched successfully.", list);
    }

    /**
     * get list of pending loans
     *
     * @return
     */
    @GetMapping("/pending")
    public ApiResponse<List<Loan>> listPendingLoans() {
        log.info("Retrieve pending loan list success");
        List<Loan> list = loanService.findAllPending();
        return new ApiResponse<>(HttpStatus.OK, "Pending Loan list fetched successfully.", list);
    }

    /**
     * get list of rejected loans
     *
     * @return
     */
    @GetMapping("/rejected")
    public ApiResponse<List<Loan>> listRejectedLoans() {
        log.info("Retrieve rejected loan list success");
        List<Loan> list = loanService.findAllRejected();
        return new ApiResponse<>(HttpStatus.OK, "Rejected Loan list fetched successfully.", list);
    }

    /**
     * assign to pickup department
     * JSON: "id"(which is loan id)
     *
     * @param loanDto
     * @return
     */
    @PutMapping
    public ApiResponse<Loan> assignToPickup(@RequestBody LoanDto loanDto) {
        loanDto.setDepartment(departmentService.findById(2));
        loanService.changeDepartment(loanDto);
        log.info("Assign loan id: " + loanDto.getId() + "to department: " + loanDto.getDepartment().getDeptName() + "success");
        return new ApiResponse<>(HttpStatus.OK, "Assign to Pickup Dept successfully.", null);
    }

}
