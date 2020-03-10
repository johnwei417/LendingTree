package com.honglin.service.impl;

import com.honglin.dao.BankDao;
import com.honglin.model.entity.Bank;
import com.honglin.model.pojo.BankDto;
import com.honglin.service.BankService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/21/20 inside the package - com.honglin.service.impl
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;

    @Override
    public List<Bank> findAll() {
        List<Bank> list = new ArrayList<>();
        bankDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Bank save(BankDto bankDto) {
        Bank newBank = new Bank();
        newBank.setBankName(bankDto.getBankName());
        //  newBank.setLoanTypes(bankDto.getLoanTypes());
        newBank.setLoans(bankDto.getLoans());

        return bankDao.save(newBank);
    }

    @Override
    public void delete(int bankId) {
        bankDao.deleteById(bankId);
    }

    @Override
    public Bank findById(int bankId) {
        return bankDao.findById(bankId).get();
    }

    @Override
    public Bank findByBankName(String bankName) {
        return bankDao.findByBankName(bankName);
    }

    @Override
    public BankDto update(BankDto bankDto) {
        Bank bank = findById(bankDto.getBankId());
        if (bank != null) {
            BeanUtils.copyProperties(bankDto, bank);

            bankDao.save(bank);
        }
        return bankDto;
    }

    @Override
    public BankDto updateBankName(BankDto bankDto) {
        Bank bank = findById(bankDto.getBankId());
        if (bank != null) {
            BeanUtils.copyProperties(bankDto, bank);
            bankDao.save(bank);
        }
        return bankDto;
    }


}
