package com.honglin.service.impl;

import com.honglin.dao.LoanStatusDao;
import com.honglin.model.entity.LoanStatus;
import com.honglin.model.pojo.LoanStatusDto;
import com.honglin.service.LoanStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/23/20 inside the package - com.honglin.service.impl
 */
@Service
public class LoanStatusServiceImpl implements LoanStatusService {

    @Autowired
    private LoanStatusDao loanStatusDao;

    @Override
    public List<LoanStatus> findAll() {
        List<LoanStatus> list = new ArrayList<>();
        loanStatusDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public LoanStatus findById(Integer id) {
        return loanStatusDao.findById(id).get();
    }

    @Override
    public LoanStatus save(LoanStatusDto loanStatusDto) {
        LoanStatus newLoanStatus = new LoanStatus();
        newLoanStatus.setStatusName(loanStatusDto.getStatusName());
        newLoanStatus.setLoans(loanStatusDto.getLoans());

        return loanStatusDao.save(newLoanStatus);
    }

    @Override
    public void delete(int loanstatusId) {
        loanStatusDao.deleteById(loanstatusId);
    }

    @Override
    public LoanStatusDto update(LoanStatusDto loanStatusDto) {
        LoanStatus loanStatus = findById(loanStatusDto.getId());
        if (loanStatus != null) {
            BeanUtils.copyProperties(loanStatusDto, loanStatus);
            loanStatusDao.save(loanStatus);
        }

        return loanStatusDto;
    }
}
