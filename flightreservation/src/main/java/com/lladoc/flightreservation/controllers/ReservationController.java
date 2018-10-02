package com.lladoc.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lladoc.flightreservation.dto.ReservationRequest;
import com.lladoc.flightreservation.entities.Flight;
import com.lladoc.flightreservation.entities.Reservation;
import com.lladoc.flightreservation.repos.FlightRepository;
import com.lladoc.flightreservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long id, ModelMap modelMap ) {
		LOGGER.info("showCompleteReservation() - flightId: "+id);
		Flight flight = flightRepository.findOne(id);
		modelMap.addAttribute("flight",flight);
		LOGGER.info("flight is : "+flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		LOGGER.info("completeReservation() - "+request);
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation created successfully and the id is "+reservation.getId());
		return "reservationConfirmation";
		
	}
}
