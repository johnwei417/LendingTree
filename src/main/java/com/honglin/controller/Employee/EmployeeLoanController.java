package com.honglin.controller.Employee;

import com.honglin.common.ApiResponse;
import com.honglin.model.entity.*;
import com.honglin.model.pojo.LoanDto;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.controller.Employee
 */

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1/loan/employee/viewloans")
public class EmployeeLoanController {
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanStatusService loanStatusService;

    @Autowired
    private EmailService emailService;

    /**
     * JSON: id (which is user id), need role based authentication later
     * setup role check in websecurityconfig
     *
     * @param userDto
     * @return
     */
    @PostMapping("/list")
    public ApiResponse<List<Loan>> listLoans(@RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        Employee employee = user.getEmployees();
        List<Loan> list = employee.getDepartment().getLoans();
        log.info("Retrieve loan list success");
        return new ApiResponse<>(HttpStatus.OK, "Loan list fetched successfully.", list);
    }

    /**
     * get customer info for that loan
     *
     * @param username
     * @return
     */
    @GetMapping("/getCustomerInfo/{username}")
    public ApiResponse<Customer> getCustomerInfo(@PathVariable String username) {

        User user = userService.findByUsername(username);
        Customer customer = user.getCustomer();
        log.info("Retrieve customer: (username: " + username + "info success");
        return new ApiResponse<>(HttpStatus.OK, "Customer information fetched successfully.", customer);
    }


    /**
     * Assign to next department
     * JSON: id (which is user id)
     *
     * @param loanId  which is passed as parameter in the url
     * @param userDto
     * @return
     */
    @PutMapping("/approve/{loanId}")
    public ApiResponse<Loan> assignToNext(@PathVariable int loanId, @RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        Employee employee = user.getEmployees();
        Department department = employee.getDepartment();

        Loan loan = loanService.findById(loanId);

        //check employee's current department id
        if (department.getId() < departmentService.countRows()) {
            loan.setDepartment(departmentService.findById(department.getId() + 1));
            //if approved at legal department, loan status need to be change to "approved"
            //and department changed to "admin"
        } else {
            //if already at legal department, if approve, set department back to "ADMIN"
            loan.setDepartment(departmentService.findById(1));

            //loan status change to "approved" which is 1 here
            loan.setLoanStatus(loanStatusService.findById(1));
            //if approved at legal department, sent email to inform the customer
            emailService.sendEmail(loan.getCustomer().getUser().getEmail(), "Your loan got approved!",
                    "Congrats! your loan got approved!");
        }

        LoanDto loanDto = new LoanDto();
        BeanUtils.copyProperties(loan, loanDto);

        loanService.changeDepartment(loanDto);
        loanService.changeLoanStatus(loanDto);
        log.info("successfully approve loan (ID:" + loanId + ") from department " + department.getDeptName());
        return new ApiResponse<>(HttpStatus.OK, "Assigned to Next Department successfully.", null);
    }

    /**
     * @param loanId which is loan id
     * @return
     */
    @PutMapping("/reject/{loanId}")
    public ApiResponse<Loan> rejectLoan(@PathVariable int loanId) {
        Loan loan = loanService.findById(loanId);

        //if reject loan, send loan back to "ADMIN" department;
        loan.setDepartment(departmentService.findById(1));

        //if reject loan, set loan status to reject which  is 2 in the database
        loan.setLoanStatus(loanStatusService.findById(2));

        LoanDto loanDto = new LoanDto();
        BeanUtils.copyProperties(loan, loanDto);

        loanService.changeDepartment(loanDto);
        loanService.changeLoanStatus(loanDto);
        log.info("successfully reject loan(ID: " + loanId + ")");
        //if rejet, send email to customer email to inform customer the loan get rejected
        emailService.sendEmail(loan.getCustomer().getUser().getEmail(), "Your loan got rejected",
                "Sorry, your loan got rejected");

        return new ApiResponse<>(HttpStatus.OK, "Reject Loan successfully.", null);
    }


}
