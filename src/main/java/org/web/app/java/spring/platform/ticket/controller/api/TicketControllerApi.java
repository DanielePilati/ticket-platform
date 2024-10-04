package org.web.app.java.spring.platform.ticket.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web.app.java.spring.platform.ticket.model.Ticket;
import org.web.app.java.spring.platform.ticket.service.TicketService;



@RestController
@RequestMapping("/api/tickets")
public class TicketControllerApi {

	@Autowired
	private TicketService ticketService;

	@GetMapping()
	public List<Ticket> index() {

		

		return ticketService.getAll();
	}

}
