package com.capstone.service;

import com.capstone.entity.Reservation;
import com.capstone.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    // Retrieve all reservations
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Create a new reservation
    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Update an existing reservation
    @Override
    public Reservation updateReservation(int id, Reservation reservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            existingReservation.setName(reservation.getName());
            existingReservation.setEmail(reservation.getEmail());
            existingReservation.setDatetime(reservation.getDatetime());
            existingReservation.setNumGuests(reservation.getNumGuests());
            return reservationRepository.save(existingReservation);
        } else {
            return null;
        }
    }

    // Delete a reservation
    @Override
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }

    // Get a reservation by email
    @Override
    public Reservation getReservationByEmail(String email) {
        return reservationRepository.findByEmail(email);
    }
}
