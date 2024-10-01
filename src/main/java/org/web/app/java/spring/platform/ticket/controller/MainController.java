package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.app.java.spring.platform.ticket.service.UserService;



@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String home(Model model, Authentication authentication) {
		
		if(authentication != null) {
			model.addAttribute("user", userService.getByUsername(authentication.getName()).get());
		}
	
		return "/pages/home";
	}
	
}
