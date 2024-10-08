package org.web.app.java.spring.platform.ticket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.Ticket;
import org.web.app.java.spring.platform.ticket.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository repo;

	public List<Ticket> getAll() {
		return repo.findAll();
	}

	public List<Ticket> getAllByUsername(String username) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (Ticket ticket : repo.findAll()) {
			if (ticket.getUser().getUsername().equals(username)) {
				tickets.add(ticket);
			}
		}
		return tickets;
	}

	public List<Ticket> getAllByType(String typeIn) {

		return repo.findByType(typeIn);
	}

	public List<Ticket> getByState(String state) {
		return repo.findByState(state);
	}

	public Optional<Ticket> getById(Integer id) {
		return repo.findById(id);
	}

	public List<Ticket> getByTitle(String title) {
		return repo.findByTitleIgnoreCaseContains(title);
	}

	public Ticket saveTicket(Ticket ticket) {
		ticket.setCreatedAt(LocalDateTime.now());
		return repo.save(ticket);

	}

	public Ticket updateTicket(Ticket ticket) {
		return repo.save(ticket);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
		;
	}

}
