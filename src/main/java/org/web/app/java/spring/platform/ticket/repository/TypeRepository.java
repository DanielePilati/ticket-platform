package org.web.app.java.spring.platform.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.platform.ticket.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

}
