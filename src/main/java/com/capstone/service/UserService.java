package com.capstone.service;

import com.capstone.dto.UserDto;
import com.capstone.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserById(int userId);
    //UserDto getUserDtoById(int userId);
    void updateUser(UserDto userDto);
    void deleteUserById(int userId);
    List<UserDto> findAllUsers();
	User findUserByEmail(String email);
	Optional<UserDto> findUserDtoById(int userId);
	Optional<UserDto> login(String email, String password);
}
