package com.honglin.service;

import com.honglin.model.entity.LoanStatus;
import com.honglin.model.pojo.LoanStatusDto;

import java.util.List;

public interface LoanStatusService {

    List<LoanStatus> findAll();

    LoanStatus findById(Integer id);

    LoanStatus save(LoanStatusDto loanStatusDto);

    void delete(int deptId);

    LoanStatusDto update(LoanStatusDto loanStatusDto);

}
