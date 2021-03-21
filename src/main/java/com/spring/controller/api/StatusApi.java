package com.spring.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.TypeStatusDTO;
import com.spring.service.ITypeStatusService;

@RestController
public class StatusApi {
	
	@Autowired
	private ITypeStatusService service;
	
    @RequestMapping(value = "/api/status-types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TypeStatusDTO> getStatusTypes() {
        List<TypeStatusDTO> list = service.findAll();
        return list;
    }
}
