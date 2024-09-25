package org.web.app.java.spring.platform.ticket.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private boolean isOpen;
	
	@ManyToOne
	@JoinColumn( name = "user_id", nullable = false)
	private User user;

	@OneToMany( mappedBy = "ticket", cascade = CascadeType.REMOVE)
	private List<Note> notes;
	
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate_creation() {
		return createdAt;
	}

	public void setDate_creation(LocalDateTime date_creation) {
		this.createdAt = date_creation;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
