package com.spring.service;

import java.util.List;

import com.spring.dto.CategoryDTO;

public interface ICategoryService {
	List<CategoryDTO> findAll();
}
