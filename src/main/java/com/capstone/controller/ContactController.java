package com.capstone.controller;

import com.capstone.entity.Contact;
import com.capstone.service.ContactService;

import jakarta.validation.Valid;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	//Home page mapping
	@GetMapping("/index")
	public String addUserForm() {
		return "index";
	}
	
    //Contact form mapping
	@GetMapping("/contact")
	public String contactForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact";
	}
	
    //Add contact
	@PostMapping("/contact")
	public String addContact(@Valid @ModelAttribute("contactDto") Contact contact, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			return "contact";
		}

		if (contactService.getContactByEmail(contact.getEmail()) == null) {
			contactService.addContact(contact);
			redirectAttributes.addFlashAttribute("successMessage", "Contact added successfully");
			return "redirect:/index";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Contact with the provided email already exists");
			return "contact";
		}
	}
	
	// Update contact
	@PostMapping("/updates/{id}")
	public String updateContact(@PathVariable("id") int id, @Valid @ModelAttribute("contact") Contact contact,
	                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	    if (bindingResult.hasErrors()) {
	        return "update";
	    }

	    try {
	        Contact existingContact = contactService.getContactByEmail(contact.getEmail());
	        if (existingContact != null && existingContact.getId() == id) {
	            contact.setId(id);
	            contactService.addContact(contact);
	            redirectAttributes.addFlashAttribute("successMessage", "Contact updated successfully");
	            return "redirect:/contacts";
	        } else {
	            redirectAttributes.addFlashAttribute("errorMessage", "Contact not found");
	            return "redirect:/contacts";
	        }
	    } catch (NoSuchElementException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Contact not found");
	        return "redirect:/contacts";
	    }
	}


	// Delete contact
	@GetMapping("/delete/{id}")
	public String deleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {
	    if (contactService.deleteContact(id)) {
	        redirectAttributes.addFlashAttribute("successMessage", "Contact deleted successfully");
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "Contact not found");
	    }

	    return "redirect:/contacts";
	}


}
