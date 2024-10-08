package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.app.java.spring.platform.ticket.model.Note;
import org.web.app.java.spring.platform.ticket.service.NoteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("note") Note formNote, BindingResult br, Model model, RedirectAttributes attributes) {

		if (br.hasErrors()) {
			Note note = new Note();
			note.setTicket(formNote.getTicket());
			model.addAttribute("note", note);
			return "/notes/create";
		}

		noteService.saveNote(formNote);
		// ALERT
		attributes.addFlashAttribute("message", "Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/tickets/show/" + formNote.getTicket().getId();
	}
	

}
