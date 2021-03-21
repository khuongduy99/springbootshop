package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findOneByName(String name);
}
