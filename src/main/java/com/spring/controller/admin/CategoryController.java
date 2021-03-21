package com.spring.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.service.ICategoryService;

@Controller
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value = { "/admin-page/category"}, method = RequestMethod.GET)
    public String categoryPage(Model model) {
		model.addAttribute("categories", categoryService.findAll());
        return "admin/category";
    }
	
	
}
