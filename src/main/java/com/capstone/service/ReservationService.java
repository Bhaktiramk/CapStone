package com.capstone.service;

import com.capstone.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();

    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(int id, Reservation reservation);

    void deleteReservation(int id);

	Reservation getReservationByEmail(String email);
}
