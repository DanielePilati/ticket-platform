package org.web.app.java.spring.platform.ticket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.platform.ticket.model.User;
import org.web.app.java.spring.platform.ticket.service.UserService;
import org.web.app.java.spring.platform.ticket.service.RoleService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping()
	public String index(Model model, Authentication authentication) {

		model.addAttribute("search", new User());
		model.addAttribute("users", userService.getAllByRole("USER"));

		return "/users/index";
	}

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

	@GetMapping("/create")
	public String create(Model model) {

		User user = new User();
		user.setNotAvailable(false);
		user.setRoles(roleService.getUserRole());
		model.addAttribute("user", user);

		return "/users/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("user") User formUser, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/users/create";
		}
		Optional<User> user = userService.getByUsername(formUser.getUsername());
		if (user.isPresent()) {
			// ALERT
			model.addAttribute("message", "This User Arleady exist");
			model.addAttribute("class", "danger");
			return "/users/create";
		}
		userService.saveUser(formUser);
		// ALERT
		attributes.addFlashAttribute("message", "Your User has been Created");
		attributes.addFlashAttribute("class", "success");
		return "redirect:/users";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model, Authentication authentication) {

		if (authentication != null) {
			model.addAttribute("user", userService.getByUsername(authentication.getName()).get());
		}

		return "/users/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("user") User formUser, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {

			return "/users/edit";
		}

		userService.updateUser(formUser);
		// ALERT
		attributes.addFlashAttribute("message", "Your User has been Updated");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/users";
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {

		userService.deleteById(id);
		// ALERT
		attributes.addFlashAttribute("message", "Your User has been Deleted");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/users";
	}

}
