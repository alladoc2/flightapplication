package com.lladoc.flightcheckin.integration;

import com.lladoc.flightcheckin.integration.dto.Reservation;
import com.lladoc.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);
	
}
