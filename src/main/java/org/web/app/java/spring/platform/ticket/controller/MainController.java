package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping()
	public String home(Model model) {

		return "/pages/home";
	}
	
}
