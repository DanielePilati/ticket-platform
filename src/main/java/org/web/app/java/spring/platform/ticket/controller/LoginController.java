package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.platform.ticket.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

	@GetMapping()
	public String login() {

		return "/pages/home";
	}

	@GetMapping("/success_handler")
	public String loginSuccessHandler(RedirectAttributes attributes, Authentication authentication) {

		// ALERT
		attributes.addFlashAttribute("message", "Welcome " + authentication.getName() + " You logged in.");
		attributes.addFlashAttribute("class", "success");
		attributes.addFlashAttribute("user", userService.getByUsername(authentication.getName()).get());

		return "redirect:/";
	}

	@PostMapping("/failure_handler")
	public String loginFailureHandler(RedirectAttributes attributes) {

		// ALERT
		attributes.addFlashAttribute("message", "Invalid Username or Password");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/";
	}

	@PostMapping("/logout")
	public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) {

		logoutHandler.logout(request, response, authentication);
		// ALERT
		attributes.addFlashAttribute("message", authentication.getName() + " You logged out.");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/";
	}

}
