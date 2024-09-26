package org.web.app.java.spring.platform.ticket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.app.java.spring.platform.ticket.model.Note;
import org.web.app.java.spring.platform.ticket.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository repo;

	public List<Note> getAll() {
		return repo.findAll();
	}

	public Optional<Note> getById(Integer id) {
		return repo.findById(id);
	}

	public Note saveNote(Note note) {
		note.setCreatedAt(LocalDateTime.now());
		return repo.save(note);

	}

	public Note updateNote(Note note) {
		return repo.save(note);
	}
	
	public void deleteById(Integer id) {
		 repo.deleteById(id);;
	}

}
