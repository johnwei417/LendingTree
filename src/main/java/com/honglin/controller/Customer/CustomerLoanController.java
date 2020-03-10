package com.honglin.controller.Customer;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.Customer;
import com.honglin.model.entity.Loan;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.LoanDto;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.controller.Customer
 */
@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/customer/loans")
public class CustomerLoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private BankService bankService;

    @Autowired
    private LoanTypeService loanTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoanStatusService loanStatusService;


    /**
     * JSON: id (user id)
     *
     * @param userDto
     * @return
     */
    @PostMapping("/list")
    public ApiResponse<List<Loan>> listLoans(@RequestBody UserDto userDto) {

        User user = userService.findById(userDto.getId());
        Customer customer = user.getCustomer();
        List<Loan> list = customer.getLoans();
        log.info("Loan list for customer (ID: " + userDto.getId() + ") retrieved successfully");
        return new ApiResponse<>(HttpStatus.OK, "Loan list fetched successfully.", list);
    }

    /**
     * JSON: id (user id)
     *
     * @param userDto
     * @return
     */
    @PostMapping("/listActive")
    public ApiResponse<List<Loan>> listActiveLoans(@RequestBody UserDto userDto) {

        User user = userService.findById(userDto.getId());
        Customer customer = user.getCustomer();
        List<Loan> list = new ArrayList<>();
        for (Loan loan : customer.getLoans()) {
            if (loan.getLoanStatusName().equals("APPROVED")) {
                list.add(loan);
            }
        }
        log.info("Approved loan list for customer (ID: " + userDto.getId() + ") retrieved successfully");
        return new ApiResponse<>(HttpStatus.OK, "Active Loan list fetched successfully.", list);
    }

    /**
     * JSON: id (user id)
     *
     * @param userDto
     * @return
     */
    @PostMapping("/listPending")
    public ApiResponse<List<Loan>> listPendingLoans(@RequestBody UserDto userDto) {

        User user = userService.findById(userDto.getId());
        Customer customer = user.getCustomer();
        List<Loan> list = new ArrayList<>();
        for (Loan loan : customer.getLoans()) {
            if (loan.getLoanStatusName().equals("PENDING")) {
                list.add(loan);
            }
        }
        log.info("Pending loan list for customer (ID: " + userDto.getId() + ") retrieved successfully");
        return new ApiResponse<>(HttpStatus.OK, "Pending Loan list fetched successfully.", list);
    }

    /**
     * JSON: id (user id)
     *
     * @param userDto
     * @return
     */
    @PostMapping("/listRejected")
    public ApiResponse<List<Loan>> listRejectedLoans(@RequestBody UserDto userDto) {

        User user = userService.findById(userDto.getId());
        Customer customer = user.getCustomer();
        List<Loan> list = new ArrayList<>();
        for (Loan loan : customer.getLoans()) {
            if (loan.getLoanStatusName().equals("REJECTED")) {
                list.add(loan);
            }
        }
        log.info("Rejected loan list for customer (ID: " + userDto.getId() + ") retrieved successfully");
        return new ApiResponse<>(HttpStatus.OK, "Rejected Loan list fetched successfully.", list);
    }


    /**
     * add new loan
     * JSON: userId (which is user id), amount (loan amount), bankId, loantypeId
     * department for new loan will automatically set as admin department;
     *
     * @param loanDto
     * @return
     */
    @PostMapping("/add")
    public ApiResponse<Loan> addLoan(@RequestBody LoanDto loanDto) {

        User user = userService.findById(loanDto.getUserId());
        Customer customer = user.getCustomer();

        LoanDto loanDto1 = new LoanDto();
        loanDto1.setAmount(loanDto.getAmount());

        //bank information
        loanDto1.setBanks(bankService.findById(loanDto.getBankId()));
        // set loan type
        loanDto1.setLoanType(loanTypeService.findById(loanDto.getLoantypeId()));

        loanDto1.setDepartment((departmentService.findById(1))); //set to admin department by default
        loanDto1.setLoanStatus(loanStatusService.findById(3)); //set to pending by default;
        loanDto1.setCustomer(customer);

        loanService.save(loanDto1);
        log.info("Customer (ID: " + customer.getId() + ") add new loan success");
        return new ApiResponse<>(HttpStatus.OK, "New Loan added successfully.", null);
    }

    /**
     * Delete a loan from customer side
     * JSON: id (which is loan id)
     *
     * @param loanDto
     * @return
     */
    @DeleteMapping
    public ApiResponse<Loan> deleteLoan(@RequestBody LoanDto loanDto) {
        Loan loan = loanService.findById(loanDto.getId());

        if (loan.getLoanStatus().getStatusName() == "PENDING" || loan.getLoanStatus().getStatusName() == "REJECTED") {
            loanService.delete(loanDto.getId());
            return new ApiResponse<>(HttpStatus.OK, "Loan deleted successfully.", null);
        }
        log.info("Loan (ID: " + loanDto + ") deleted success");
        return new ApiResponse<>(HttpStatus.OK, "Cannot delete because it already approved.", null);
    }

}
