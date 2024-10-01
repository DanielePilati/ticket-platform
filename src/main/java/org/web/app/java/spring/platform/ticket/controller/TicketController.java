package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.platform.ticket.model.Note;
import org.web.app.java.spring.platform.ticket.model.Ticket;
import org.web.app.java.spring.platform.ticket.service.TicketService;
import org.web.app.java.spring.platform.ticket.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;

	private final String[] TICKET_STATES = { "ToDo", "InProgress", "Completed" };

	@GetMapping()
	public String index(Model model, Authentication authentication) {

		model.addAttribute("search", new Ticket());
		model.addAttribute("states", this.TICKET_STATES);
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("ADMIN")) {
				model.addAttribute("tickets", ticketService.getAll());
			} else if (authority.getAuthority().equals("USER")) {
				model.addAttribute("tickets", ticketService.getAllByUsername(authentication.getName()));
			}
		}

		return "/tickets/index";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {

		model.addAttribute("ticket", ticketService.getById(id).get());

		return "/tickets/show";
	}

	@GetMapping("/show/{id}/addnote")
	public String createNote(@PathVariable(name = "id") Integer id, Model model, Authentication authentication) {

		Note note = new Note();
		note.setTicket(ticketService.getById(id).get());
		note.setAuthor(authentication.getName());
		model.addAttribute("note", note);

		return "/notes/create";
	}

	@GetMapping("/search/")
	public String search(@RequestParam(name = "title") String title, Model model) {

		model.addAttribute("search", new Ticket());
		model.addAttribute("tickets", ticketService.getByTitle(title));

		return "/tickets/index";
	}

	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("ticket", new Ticket());
		model.addAttribute("states", this.TICKET_STATES);
		model.addAttribute("availableUsers", userService.getUsersAvailable());

		return "/tickets/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			model.addAttribute("states", this.TICKET_STATES);
			model.addAttribute("availableUsers", userService.getUsersAvailable());
			return "/tickets/create";
		}

		ticketService.saveTicket(formTicket);

		attributes.addFlashAttribute("tickets", ticketService.getAll());
		// ALERT
		attributes.addFlashAttribute("message", "Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/tickets";

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model) {

		model.addAttribute("ticket", ticketService.getById(id).get());
		model.addAttribute("states", this.TICKET_STATES);
		model.addAttribute("availableUsers", userService.getUsersAvailable());

		return "/tickets/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			model.addAttribute("states", this.TICKET_STATES);
			model.addAttribute("availableUsers", userService.getUsersAvailable());
			return "/tickets/edit";
		}

		ticketService.updateTicket(formTicket);
		// ALERT
		attributes.addFlashAttribute("message", "Updated");
		attributes.addFlashAttribute("class", "warning");

		return "redirect:/tickets";
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {

		ticketService.deleteById(id);
		// ALERT
		attributes.addFlashAttribute("message", "Deleted");
		attributes.addFlashAttribute("class", "danger");

		return "redirect:/tickets";
	}

}
