package com.honglin.service.impl;

import com.honglin.dao.CustomerDao;
import com.honglin.model.entity.Customer;
import com.honglin.model.pojo.CustomerDto;
import com.honglin.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * honglinwei created on 2/21/20 inside the package - com.honglin.service.impl
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;


    @Override
    public Customer save(CustomerDto customerDto) {
        Customer newUser = new Customer();
        newUser.setSalary(customerDto.getSalary());
        newUser.setUser(customerDto.getUser());
        newUser.setLoans(customerDto.getLoans());
        return customerDao.save(newUser);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        customerDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(int id) {
        customerDao.deleteById(id);
    }

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id).get();
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Customer customer = findById(customerDto.getId());
        if (customer != null) {
            BeanUtils.copyProperties(customerDto, customer);
            customerDao.save(customer);
        }

        return customerDto;
    }


}
