package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByEmail(String email);
	UserEntity findOneByFullName(String name);
}