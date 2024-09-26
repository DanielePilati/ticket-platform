package org.web.app.java.spring.platform.ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.User;
import org.web.app.java.spring.platform.ticket.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public Optional<User> getByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public List<User> getUsersAvailable(){
		return repo.findAvailableUser();
	}

}
