package com.honglin.dao;

import com.honglin.model.entity.LoanType;
import org.springframework.data.repository.CrudRepository;

public interface LoanTypeDao extends CrudRepository<LoanType, Integer> {
}
