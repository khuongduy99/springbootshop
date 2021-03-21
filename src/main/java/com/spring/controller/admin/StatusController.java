package com.spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.service.IStatusService;

@Controller
public class StatusController {
	
 @Autowired
 private IStatusService statusService;
	@RequestMapping(value = { "/admin-page/status"}, method = RequestMethod.GET)
    public String statusPage(Model model, @RequestParam(value="type", required = false) String type) {
		if(type == null) type = "";
		model.addAttribute("ListStatus", statusService.findAllByTypeStatus(type));
        return "admin/status";
    }
}
