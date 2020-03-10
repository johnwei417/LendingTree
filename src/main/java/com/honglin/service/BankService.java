package com.honglin.service;

import com.honglin.model.entity.Bank;
import com.honglin.model.pojo.BankDto;

import java.util.List;

/**
 * honglinwei created on 2/21/20 inside the package - com.honglin.service
 */
public interface BankService {

    List<Bank> findAll();

    Bank save(BankDto bankDto);

    void delete(int bankId);

    Bank findById(int bankId);

    Bank findByBankName(String bankName);

    BankDto update(BankDto bankDto);

    BankDto updateBankName(BankDto bankDto);

}
