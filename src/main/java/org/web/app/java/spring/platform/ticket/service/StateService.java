package org.web.app.java.spring.platform.ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.State;
import org.web.app.java.spring.platform.ticket.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;

	public List<State> getAll() {
		return repo.findAll();
	}

	public Optional<State> getById(Integer id) {
		return repo.findById(id);
	}
	
	public Optional<State> getByName(String name) {
		return repo.findByName(name);
	}

	public State saveState(State note) {
		return repo.save(note);

	}

	public State updateState(State state) {
		return repo.save(state);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
		;
	}

}
