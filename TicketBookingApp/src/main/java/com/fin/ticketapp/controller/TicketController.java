package com.fin.ticketapp.controller;

import com.fin.ticketapp.model.Ticket;
import com.fin.ticketapp.service.TicketBookingService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
	@Autowired
	TicketBookingService ticketBookingService;

	@PostMapping("/tickets")
	public Ticket SaveTicket(@RequestBody Ticket ticket) {

		return ticketBookingService.createTicket(ticket);

	}

	@GetMapping("/tickets/{id}")
	public Ticket findTicket(@PathVariable("id") Long ticket) throws Exception {

		return ticketBookingService.getTicketById(ticket);

	}

	@GetMapping("/tickets")
	public List<Ticket> findAllTickets() {

		return ticketBookingService.getAllBookedTickets();

	}

	@GetMapping(value = "/tickets/email/{email:.+}")
	public Ticket getTicketByEmail(@PathVariable("email") String email) {
		return ticketBookingService.getTicketByEmail(email);
	}

}
