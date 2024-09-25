package org.web.app.java.spring.platform.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
}
