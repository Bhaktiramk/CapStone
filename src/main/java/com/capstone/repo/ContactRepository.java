package com.capstone.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.entity.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

	Optional<Contact> findByEmail(String email);

}
