package org.web.app.java.spring.platform.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		attributes.addFlashAttribute("message", "Your Note has been Created");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/tickets/show/" + formNote.getTicket().getId();
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") Integer id, Model model, Authentication authentication) {

		Note note = noteService.getById(id).get();
		model.addAttribute("note", note);

		return "/notes/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("note") Note formNote, BindingResult br, Model model,
			RedirectAttributes attributes) {

		if (br.hasErrors()) {
			return "/notes/edit";
		}

		noteService.updateNote(formNote);
		// ALERT
		attributes.addFlashAttribute("message", "Your Note has been Updated");
		attributes.addFlashAttribute("class", "success");

		return "redirect:/tickets/show/" + formNote.getTicket().getId();
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {

		Integer ticketId = noteService.getById(id).get().getTicket().getId();
		noteService.deleteById(id);
		// ALERT
		attributes.addFlashAttribute("message", "Your Note has been Deleted");
		attributes.addFlashAttribute("class", "danger");
		
		

		return "redirect:/tickets/show/"+ ticketId;
	}
	

}
