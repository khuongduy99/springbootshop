package com.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.TypeStatusDTO;
import com.spring.entity.TypeStatusEntity;
import com.spring.repository.TypeStatusRepository;
import com.spring.service.ITypeStatusService;

@Service
public class TypeStatusService implements ITypeStatusService {

	@Autowired
	private TypeStatusRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TypeStatusDTO> findAll() {
		List<TypeStatusDTO> results = new ArrayList<TypeStatusDTO>();
		List<TypeStatusEntity> entities = repository.findAll();

		for (TypeStatusEntity item : entities) {
			TypeStatusDTO dto = modelMapper.map(item, TypeStatusDTO.class);
			results.add(dto);
		}
		return results;
	}

	@Override
	public TypeStatusDTO findOneByAlias(String alias) {
		TypeStatusEntity entity = repository.findOneByAlias(alias);
		if(entity == null) return null;
		return modelMapper.map(entity, TypeStatusDTO.class);
	}

}
