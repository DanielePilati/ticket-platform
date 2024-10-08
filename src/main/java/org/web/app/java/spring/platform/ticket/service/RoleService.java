package org.web.app.java.spring.platform.ticket.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.Role;
import org.web.app.java.spring.platform.ticket.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repo;

	public Set<Role> getUserRole() {
		return repo.findUserRole();
	}

}
