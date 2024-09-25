package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.platform.ticket.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping()
	public String index(Model model, RedirectAttributes attributes) {
		model.addAttribute("tickets", ticketService.getAll());
		return "/tickets/index";
	}
	@GetMapping("/{word}")
	public String find(@PathVariable(name = "word") String word, Model model, RedirectAttributes attributes) {
			model.addAttribute("tickets", ticketService.getByTitle(word).get());
			return "/tickets/index";
	}

	


}
