package com.ruchira.learn.springsecurity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruchira.learn.springsecurity.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findByUsername(String username);
}
