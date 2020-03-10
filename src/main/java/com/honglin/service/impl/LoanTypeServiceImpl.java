package com.honglin.service.impl;

import com.honglin.dao.LoanTypeDao;
import com.honglin.model.entity.LoanType;
import com.honglin.model.pojo.LoanTypeDto;
import com.honglin.service.LoanTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/22/20 inside the package - com.honglin.service.impl
 */
@Service
public class LoanTypeServiceImpl implements LoanTypeService {

    @Autowired
    private LoanTypeDao loanTypeDao;


    @Override
    public List<LoanType> findAll() {
        List<LoanType> list = new ArrayList<>();
        loanTypeDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public LoanType save(LoanTypeDto loanTypeDto) {
        LoanType newLoanType = new LoanType();
        newLoanType.setLoanType(loanTypeDto.getLoanType());

        return loanTypeDao.save(newLoanType);
    }

    @Override
    public void delete(int loantypeId) {
        loanTypeDao.deleteById(loantypeId);
    }

    @Override
    public LoanType findById(int loantypeId) {
        return loanTypeDao.findById(loantypeId).get();
    }

    @Override
    public LoanTypeDto update(LoanTypeDto loanTypeDto) {
        LoanType loanType = findById(loanTypeDto.getLoanTypeId());
        if (loanType != null) {
            BeanUtils.copyProperties(loanTypeDto, loanType);
            loanTypeDao.save(loanType);
        }
        return loanTypeDto;
    }
}
