package com.honglin.dao;

import com.honglin.model.entity.LoanStatus;
import org.springframework.data.repository.CrudRepository;

/**
 * honglinwei created on 2/23/20 inside the package - com.honglin.dao
 */
public interface LoanStatusDao extends CrudRepository<LoanStatus, Integer> {
}
