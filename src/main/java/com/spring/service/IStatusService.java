package com.spring.service;

import java.util.List;

import com.spring.dto.StatusDTO;

public interface IStatusService {
	List<StatusDTO> findAllByTypeStatus(String type);
}
