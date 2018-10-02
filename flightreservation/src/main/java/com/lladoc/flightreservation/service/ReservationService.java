package com.lladoc.flightreservation.service;

import com.lladoc.flightreservation.dto.ReservationRequest;
import com.lladoc.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
