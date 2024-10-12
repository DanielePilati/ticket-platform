package org.web.app.java.spring.platform.ticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.platform.ticket.model.State;

public interface StateRepository extends JpaRepository<State, Integer> {

	Optional<State> findById(Integer id);
	
	Optional<State> findByName(String name);


}
