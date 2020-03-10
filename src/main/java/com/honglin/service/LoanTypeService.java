package com.honglin.service;

import com.honglin.model.entity.LoanType;
import com.honglin.model.pojo.LoanTypeDto;

import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.service
 */
public interface LoanTypeService {


    List<LoanType> findAll();

    LoanType save(LoanTypeDto loanTypeDto);

    void delete(int loantypeId);

    LoanType findById(int bankId);

    LoanTypeDto update(LoanTypeDto loanTypeDto);
}
