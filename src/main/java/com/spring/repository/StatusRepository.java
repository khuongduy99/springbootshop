package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.StatusEntity;
import com.spring.entity.TypeStatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Long>{
	List<StatusEntity> findAllByTypeStatus(TypeStatusEntity typestatus);
}
