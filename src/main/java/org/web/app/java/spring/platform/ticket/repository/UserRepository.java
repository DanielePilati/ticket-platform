package org.web.app.java.spring.platform.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.web.app.java.spring.platform.ticket.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	@Query(value = "SELECT * FROM users u WHERE u.not_available = 0 and not u.username = 'Operator Not Available'", nativeQuery = true)
	List<User> findAvailableUser();

}
