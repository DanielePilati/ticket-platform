package org.web.app.java.spring.platform.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.web.app.java.spring.platform.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	Optional<Ticket> findById(Integer id);
	
	@Query(value = "SELECT * FROM tickets t WHERE t.state = :state", nativeQuery = true)
	List<Ticket> findByState(String state);
	
	@Query(value = "SELECT t.* FROM tickets t left join ticket_type tt on t.ticket_id = tt.ticket_id left join types t2 on t2.type_id = tt.type_id where t2.name = :type", nativeQuery = true)
	List<Ticket> findByType(String type);
	
	List<Ticket> findByTitleIgnoreCaseContains(String title);

}
