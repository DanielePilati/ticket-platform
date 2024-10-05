package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.platform.ticket.model.User;
import org.web.app.java.spring.platform.ticket.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/changestatus")
	public String setNotAvailable(RedirectAttributes attributes, Authentication authentication) {

		User user = userService.getByUsername(authentication.getName()).get();
		if (!user.isNotAvailable()) {
			user.setNotAvailable(true);
		} else {
			user.setNotAvailable(false);
		}
		userService.updateUser(user);
		attributes.addFlashAttribute("user", user);

		return "redirect:/";
	}

}
