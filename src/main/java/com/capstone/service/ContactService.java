package com.capstone.service;

import com.capstone.entity.Contact;

import jakarta.validation.Valid;

import java.util.List;

public interface ContactService {
	
	List<Contact> getAllContacts();

    Contact addContact(@Valid Contact contact);

	Contact updateContact(int id, Contact contact);

	boolean deleteContact(int id);

	Contact getContactByEmail(String email);

 
}
