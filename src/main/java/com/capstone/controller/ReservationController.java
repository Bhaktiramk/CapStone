package com.capstone.controller;

import com.capstone.entity.Reservation;
import com.capstone.service.ReservationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    // Home page mapping
    @GetMapping("/home")
    public String addUserForm() {
        return "index";
    }

    // Show reservation form
    @GetMapping("/Reservation")
    public String showReservationForm(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        return "Reservation";
    }

    // Create a new reservation
    @PostMapping("/Reservation")
    public String createReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return "Reservation";
        }

        Reservation existingReservation = reservationService.getReservationByEmail(reservation.getEmail());
        if (existingReservation == null) {
            reservationService.createReservation(reservation);
            redirectAttributes.addFlashAttribute("successMessage", "Reservation created successfully");
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Reservation with the provided email already exists");
            return "Reservation";
        }
    }

    // Update an existing reservation
    @PutMapping("/reservation/{id}")
    public String updateReservation(@PathVariable int id, @ModelAttribute Reservation reservation) {
        reservationService.updateReservation(id, reservation);
        return "redirect:/Reservation";
    }

    // Delete a reservation
    @DeleteMapping("/reservation/{id}")
    public String deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return "redirect:/Reservation";
    }
}
