package com.lladoc.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lladoc.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
