package com.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.StatusDTO;
import com.spring.dto.TypeStatusDTO;
import com.spring.entity.StatusEntity;
import com.spring.entity.TypeStatusEntity;
import com.spring.repository.StatusRepository;
import com.spring.service.IStatusService;
import com.spring.service.ITypeStatusService;


@Service
public class StatusService implements IStatusService{
	
	@Autowired
	private ITypeStatusService typeStatusService;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StatusDTO> findAllByTypeStatus(String type) {
		List<StatusDTO> result = new ArrayList<StatusDTO>();
		TypeStatusDTO typeStatusDto = typeStatusService.findOneByAlias(type);
		if(typeStatusDto == null) return result;
		TypeStatusEntity typeStatus = modelMapper.map(typeStatusDto, TypeStatusEntity.class);
		List<StatusEntity> entities = statusRepository.findAllByTypeStatus(typeStatus);
		for(StatusEntity item : entities) {
			StatusDTO dto = modelMapper.map(item, StatusDTO.class);
			result.add(dto);
		}
		
		return result;
	}

}
