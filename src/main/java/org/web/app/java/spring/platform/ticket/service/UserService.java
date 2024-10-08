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
		return repo.findByUsernameContains(username);
	}
	
	public List<User> getUsersAvailable(){
		return repo.findAvailableUser();
	}
	
	public Optional<User> getById(Integer id){
		return repo.findById(id);
	}
	
	public User updateUser(User user) {
		user.setPassword("{noop}"+user.getPassword());
		return repo.save(user);
	}
	
	public List<User> getAllByRole(String role){	
		return repo.findByRole(role);
	}
	
	public User saveUser(User user) {
		user = repo.save(user);
		user.setPassword("{noop}"+ user.getPassword());
		return user;
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

}
