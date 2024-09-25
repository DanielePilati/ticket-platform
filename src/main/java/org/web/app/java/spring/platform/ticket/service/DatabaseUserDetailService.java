package org.web.app.java.spring.platform.ticket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.User;
import org.web.app.java.spring.platform.ticket.security.DatabaseUserDetails;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userService.getByUsername(username);
		
		if(user.isPresent()) {
			return new DatabaseUserDetails(user.get());
		} else throw new UsernameNotFoundException("Username "+ username +" not found");
		
	}

}
