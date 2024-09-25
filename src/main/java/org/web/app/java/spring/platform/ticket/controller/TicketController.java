package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.app.java.spring.platform.ticket.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/{word}")
	public String find(@PathVariable(name = "word") String word, Model model) {
			model.addAttribute("tickets", ticketService.getByTitle(word));
			return "/tickets/index";
	}
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("tickets", ticketService.getAll());
		return "/tickets/index";
	}
	


}
