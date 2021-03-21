package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.TypeStatusEntity;

public interface TypeStatusRepository extends JpaRepository<TypeStatusEntity, Long>{
	TypeStatusEntity findOneByAlias(String alias);
}
