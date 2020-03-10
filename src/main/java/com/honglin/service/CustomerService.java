package com.honglin.service;

import com.honglin.model.entity.Customer;
import com.honglin.model.pojo.CustomerDto;

import java.util.List;

public interface CustomerService {


    Customer save(CustomerDto customerDto);

    List<Customer> findAll();

    void delete(int id);

    Customer findById(int id);

    CustomerDto update(CustomerDto customerDto);


}
