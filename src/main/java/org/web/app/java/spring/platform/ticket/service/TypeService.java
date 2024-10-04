package org.web.app.java.spring.platform.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.Type;
import org.web.app.java.spring.platform.ticket.repository.TypeRepository;

@Service
public class TypeService {

	@Autowired
	private TypeRepository repo;

	public List<Type> getAll() {
		return repo.findAll();
	}

}
