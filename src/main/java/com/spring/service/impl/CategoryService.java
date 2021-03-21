package com.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.CategoryDTO;
import com.spring.entity.CategoryEntity;
import com.spring.repository.CategoryRepository;
import com.spring.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> results = new ArrayList<CategoryDTO>();
		List<CategoryEntity> entities = categoryRepository.findAll();

		for (CategoryEntity item : entities) {
			CategoryDTO dto = modelMapper.map(item, CategoryDTO.class);
			results.add(dto);

		}
		return results;
	}

}
