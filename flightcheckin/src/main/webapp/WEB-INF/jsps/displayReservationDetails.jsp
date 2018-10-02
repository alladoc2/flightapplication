<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservation Details</title>
</head>
<body>
<h2>Flight Reservation</h2>
<br/>
Airlines:${reservation.flight.operatingAirlines}<br/>
Flight Number:${reservation.flight.flightNumber}<br/>
Departure City:${reservation.flight.departureCity}<br/>
Arrival City:${reservation.flight.arrivalCity}<br/>
Date of Departure:${reservation.flight.dateOfDeparture}<br/>
Estimated Departure Time:${reservation.flight.estimatedDepartureTime}<br/>
<br/>
<h2>Passenger Details</h2>
First Name: ${reservation.passenger.firstName}<br/>
Last Name: ${reservation.passenger.lastName}<br/>
Email: ${reservation.passenger.email}<br/>
Phone: ${reservation.passenger.phone}<br/>
<br/>
<form action="completeCheckIn" method="post">
Enter the number of bags you want to check in:<input type="text" name="numberOfBags" /><br/>
<input type="hidden" value="${reservation.id}" name="reservationId" /><br/>
<input type="submit" value="Check In" />
</form>
</body>
</html>