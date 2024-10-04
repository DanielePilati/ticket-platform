package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.app.java.spring.platform.ticket.model.User;
import org.web.app.java.spring.platform.ticket.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/changestatus")
	public String setNotAvailable(Model model, Authentication authentication) {

		User user = userService.getByUsername(authentication.getName()).get();
		if (!user.isNotAvailable()) {
			user.setNotAvailable(true);
		}
		userService.updateUser(user);
		model.addAttribute("user", user);

		return "/pages/home";
	}

}
