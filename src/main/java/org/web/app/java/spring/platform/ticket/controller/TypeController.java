package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.app.java.spring.platform.ticket.service.TypeService;



@Controller
@RequestMapping("/types")
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	@GetMapping()
	public String home(Model model, Authentication authentication) {

		model.addAttribute("types", typeService.getAll());
	
		return "/types/index";
	}
	
}
