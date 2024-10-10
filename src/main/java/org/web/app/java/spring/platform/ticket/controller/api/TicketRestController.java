package org.web.app.java.spring.platform.ticket.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Ticket>> index() {
		List<Ticket> tickets = ticketService.getAll();

		if (tickets.size() <= 0) {
			return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
	}

	@GetMapping("/state/{state}")
	public ResponseEntity<List<Ticket>> showByState(@PathVariable(name = "state") String state) {

		List<Ticket> tickets = ticketService.getByState(state);
		if (tickets.size() <= 0) {
			return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);

	}

	@GetMapping("/category/{type}")
	public ResponseEntity<List<Ticket>> showByType(@PathVariable(name = "type") String type) {

		List<Ticket> tickets = ticketService.getAllByType(type);
		if (tickets.size() <= 0) {
			return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);

	}

}
