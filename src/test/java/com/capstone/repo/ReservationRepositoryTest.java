package com.capstone.repo;

import com.capstone.entity.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void testFindByEmail() {
        // Create a test reservation
        Reservation reservation = new Reservation();
        reservation.setName("John Doe");
        reservation.setEmail("johndoe@example.com");
        reservation.setDatetime(LocalDateTime.now());
        reservation.setNumGuests(4);
        entityManager.persist(reservation);
        entityManager.flush();

        // Call the repository method
        Reservation foundReservation = reservationRepository.findByEmail("johndoe@example.com");

        // Verify the result
        Assertions.assertNotNull(foundReservation);
        Assertions.assertEquals("John Doe", foundReservation.getName());
        Assertions.assertEquals("johndoe@example.com", foundReservation.getEmail());
    }
}
