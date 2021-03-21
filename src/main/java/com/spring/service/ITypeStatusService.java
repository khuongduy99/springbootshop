package com.spring.service;

import java.util.List;

import com.spring.dto.TypeStatusDTO;

public interface ITypeStatusService {
	List<TypeStatusDTO> findAll();
	
	TypeStatusDTO findOneByAlias(String alias);
}
