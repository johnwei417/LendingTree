package com.honglin.dao;

import com.honglin.model.entity.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * honglinwei created on 2/18/20 inside the package - com.honglin.dao
 */

@Repository
public interface BankDao extends CrudRepository<Bank, Integer> {

    Bank findByBankName(String bankName);
}
