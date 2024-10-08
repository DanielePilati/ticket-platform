package org.web.app.java.spring.platform.ticket.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.web.app.java.spring.platform.ticket.model.Role;
import org.web.app.java.spring.platform.ticket.model.Ticket;
import org.web.app.java.spring.platform.ticket.model.User;


@SuppressWarnings("serial")
public class DatabaseUserDetails implements UserDetails {

	private final Integer id;
	private final String username;
	private final String password;
	private final String email;
	private final boolean notAvailable;
	private final List<Ticket> tickets;
	private final Set<GrantedAuthority> authorities;

	public DatabaseUserDetails(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.notAvailable = user.isNotAvailable();
		this.tickets = user.getTickets();

		authorities = new HashSet<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		return authorities;
	}

	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public Integer getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public boolean isNotAvailable() {
		return this.notAvailable;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

}
