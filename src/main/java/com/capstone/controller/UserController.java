package com.capstone.controller;

import com.capstone.dto.UserDto;
import com.capstone.entity.User;
import com.capstone.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    // Home page mapping
    @GetMapping("/")
    public String addUserForm() {
        return "index";
    }

    // Signup form mapping
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        System.out.println("Test 1");
        return "signup";
    }

    // Add user
    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        System.out.println("Test 2");

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        
        if (userService.findUserByEmail(userDto.getEmail()) == null) {
            userService.saveUser(userDto);
            redirectAttributes.addFlashAttribute("successMessage", "User added successfully");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User with the provided email already exists");
            return "signup";
        }
    }

    // Login page mapping
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Login view
    @PostMapping("/login")
    public String loginView(Model model, @ModelAttribute("signupSuccess") String signupSuccess) {
        if (signupSuccess.equalsIgnoreCase("true")) {
        	
            model.addAttribute("signupSuccess", "true");
        } else {
            model.addAttribute("signupSuccess", "false");
        }
        return "index";
    }

    // Update user
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid @ModelAttribute("userDto") UserDto userDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update";
        }

        try {
            Optional<UserDto> existingUser = userService.findUserDtoById(id);
            if (existingUser.isPresent()) {
                userService.updateUser(userDto);
                redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
                return "redirect:/users";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "User not found");
                return "redirect:/users";
            }
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            return "redirect:/users";
        }
    }

   // Delete user
    @GetMapping("/deletes/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            User existingUser = userService.findUserById(id);
            if (existingUser != null) {
                userService.deleteUserById(id);
                redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            }
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
        }

        return "redirect:/users";
    }
}
