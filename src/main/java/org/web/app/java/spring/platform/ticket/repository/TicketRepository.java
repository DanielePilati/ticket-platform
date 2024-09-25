package org.web.app.java.spring.platform.ticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.platform.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	Optional<Ticket> findById(Integer id);

	Optional<Ticket> findByTitleIgnoreCaseContains(String title);

}
