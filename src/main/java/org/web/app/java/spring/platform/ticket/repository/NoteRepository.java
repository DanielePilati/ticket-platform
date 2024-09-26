package org.web.app.java.spring.platform.ticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.app.java.spring.platform.ticket.model.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	Optional<Note> findById(Integer id);


}
