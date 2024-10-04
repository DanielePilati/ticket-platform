package org.web.app.java.spring.platform.ticket.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web.app.java.spring.platform.ticket.model.Ticket;
import org.web.app.java.spring.platform.ticket.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

	@Autowired
	private TicketService ticketService;

	@GetMapping()
	public List<Ticket> index() {

		return ticketService.getAll();
	}

	@GetMapping("/{state}")
	public List<Ticket> showByState(@PathVariable(name = "state") String state) {

		return ticketService.getByState(state);

	}
	
	@GetMapping("/category/{type}")
	public List<Ticket> showByType(@PathVariable(name = "type") String type) {

		return ticketService.getAllByType(type);

	}

}
