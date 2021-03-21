package com.spring.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMainController {
	@RequestMapping(value = { "/admin-page"}, method = RequestMethod.GET)
    public String adminHome(Model model) {
        return "admin/index";
    }
}
