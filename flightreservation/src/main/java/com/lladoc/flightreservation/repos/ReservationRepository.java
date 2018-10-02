package com.lladoc.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lladoc.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
