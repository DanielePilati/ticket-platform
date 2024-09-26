package org.web.app.java.spring.platform.ticket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.Ticket;
import org.web.app.java.spring.platform.ticket.model.User;
import org.web.app.java.spring.platform.ticket.repository.TicketRepository;
import org.web.app.java.spring.platform.ticket.repository.UserRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository repo;

	@Autowired
	private UserRepository userRepo;

	public List<Ticket> getAll() {
		return repo.findAll();
	}

	public Optional<Ticket> getById(Integer id) {
		return repo.findById(id);
	}

	public Optional<Ticket> getByTitle(String title) {
		return repo.findByTitleIgnoreCaseContains(title);
	}

	public Ticket saveTicket(Ticket ticket) {

		ticket.setCreatedAt(LocalDateTime.now());

		if (ticket.getUser() == null) {
			for (User user : userRepo.findAll()) {
				if (!user.isNotAvalable()) {
					ticket.setUser(user);
					break;
				}
			}
		}
		return repo.save(ticket);

	}

	public Ticket updateTicket(Ticket ticket) {
		return repo.save(ticket);
	}

}
