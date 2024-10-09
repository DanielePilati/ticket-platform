package org.web.app.java.spring.platform.ticket.controller;

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
import org.web.app.java.spring.platform.ticket.model.Type;
import org.web.app.java.spring.platform.ticket.service.TypeService;

import jakarta.validation.Valid;

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

	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("type", new Type());

		return "/types/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("type") Type typeForm, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/types/create";
		}

		typeService.saveType(typeForm);
		// ALERT
		attributes.addFlashAttribute("message", "Your Category has been Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/types";
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {

		typeService.deleteById(id);
		// ALERT
		attributes.addFlashAttribute("message", "Your Category has been Deleted");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/types";
	}
}
