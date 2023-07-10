package com.capstone.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.dto.UserDto;
import com.capstone.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);

	Optional<UserDto> findByEmailAndPassword(String email, String password);

}
