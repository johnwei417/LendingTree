package com.honglin.service.impl;

import com.honglin.dao.RoleDao;
import com.honglin.model.entity.Roles;
import com.honglin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * honglinwei created on 2/18/20 inside the package - com.honglin.service.impl
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public Roles getAuthorityById(Integer id) {
        return roleDao.findById(id).get();
    }
}
