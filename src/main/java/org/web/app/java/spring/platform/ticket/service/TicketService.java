package org.web.app.java.spring.platform.ticket.service;

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

	public Optional<Ticket> getById(Integer id) {
		return repo.findById(id);
	}

	public Optional<Ticket> getByTitle(String title) {
		return repo.findByTitleIgnoreCaseContains(title);
	}

}
