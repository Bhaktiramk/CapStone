package com.capstone.service;
import com.capstone.entity.Contact;
import com.capstone.repo.ContactRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private  ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactByEmail(String email) {
        Optional<Contact> optionalContact = contactRepository.findByEmail(email);
        return optionalContact.orElse(null);
    }


    @Override
    public Contact addContact(@Valid Contact contact) {
        return contactRepository.save(contact);
    }


    @Override
    public Contact updateContact(int id, Contact contact) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setFirstName(contact.getFirstName());
            existingContact.setLastName(contact.getLastName());
            existingContact.setEmail(contact.getEmail());
            existingContact.setPhoneNumber(contact.getPhoneNumber());
            existingContact.setMessage(contact.getMessage());
            return contactRepository.save(existingContact);
        }
        return null;
    }

    @Override
    public boolean deleteContact(int id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
