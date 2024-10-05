package org.web.app.java.spring.platform.ticket.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "The content must contain at least one non-whitespace character")
	private String title;

	@NotBlank(message = "The content must contain at least one non-whitespace character")
	@Column(columnDefinition = "TEXT")
	private String text;

	private LocalDateTime createdAt;

	@NotEmpty(message = "The content must not be empty")
	private String state;

	@NotNull(message = "The content must not be empty")
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "ticket", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private List<Note> notes;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable( 
			name = "ticket_type", 
			joinColumns = @JoinColumn(name = "ticket_id"), 
			inverseJoinColumns = @JoinColumn(name = "type_id")
			)
	@JsonManagedReference
	private List<Type> types;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDateFormatted() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");

		return this.getCreatedAt().format(formatter);
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

}
