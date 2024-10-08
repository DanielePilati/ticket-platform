package org.web.app.java.spring.platform.ticket.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.web.app.java.spring.platform.ticket.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value = "SELECT * FROM roles r WHERE r.name = 'USER'", nativeQuery = true)
	Set<Role> findUserRole();

}
