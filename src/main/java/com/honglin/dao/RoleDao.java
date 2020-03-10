package com.honglin.dao;

import com.honglin.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * honglinwei created on 2/18/20 inside the package - com.honglin.dao
 */
@Repository
public interface RoleDao extends JpaRepository<Roles, Integer> {
}
