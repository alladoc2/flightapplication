package com.lladoc.flightreservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lladoc.flightreservation.controllers.ReservationController;
import com.lladoc.flightreservation.dto.ReservationRequest;
import com.lladoc.flightreservation.entities.Flight;
import com.lladoc.flightreservation.entities.Passenger;
import com.lladoc.flightreservation.entities.Reservation;
import com.lladoc.flightreservation.repos.FlightRepository;
import com.lladoc.flightreservation.repos.PassengerRepository;
import com.lladoc.flightreservation.repos.ReservationRepository;
import com.lladoc.flightreservation.util.EmailUtil;
import com.lladoc.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.lladoc.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {

		LOGGER.info("Inside bookFlight()");
		// Make Payment
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flihgt id: "+ flightId);
		Flight flight = flightRepository.findOne(flightId);
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger: "+passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving the reservation: "+reservation);
		Reservation saveReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR+saveReservation.getId()+".pdf";
		LOGGER.info("Generating theItenerary");
		pdfGenerator.generateItinerary(saveReservation, filePath);
		LOGGER.info("Sending Email the Itenerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return saveReservation;
	}

}
