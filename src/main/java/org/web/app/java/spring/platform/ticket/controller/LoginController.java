package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping()
	public String login() {

		return "/pages/home";
	}
	
	  @GetMapping("/success_handler")
	  public String loginSuccessHandler() {
	 
		  
	    return "/";
	  }

	  @PostMapping("/failure_handler")
	  public String loginFailureHandler() {
	   
		  
	    return "login";
	  }
	
}
