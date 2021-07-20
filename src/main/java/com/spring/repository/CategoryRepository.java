package com.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.CategoryEntity;
@Transactional
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByAlias(String alias);
	List<CategoryEntity> findAll();
	List<CategoryEntity> findAllByIsAccessoryAndStatus(boolean isAccessory, String status);
	List<CategoryEntity> findAllByIsAccessory(boolean isAccessory);
	int countByIsAccessory(boolean isAccessory);
	
	@Query(value = "DELETE FROM category WHERE category.id = ?1;", nativeQuery = true)
    void deleteById(Long id);
	CategoryEntity findOne(Long id);
}
