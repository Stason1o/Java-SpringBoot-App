package com.sbogdanschi.springboot.repository;

import com.sbogdanschi.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleType(String role);

	Role findById(Integer id);

}
