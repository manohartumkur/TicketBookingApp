package com.fin.ticketapp.service;

import com.fin.ticketapp.model.Ticket;
import com.fin.ticketapp.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketBookingService {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public Ticket createTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket getTicketById(Long ticketId) throws Exception {
		return ticketRepository.findById(ticketId)
				.orElseThrow(() -> new TicketNotFoundException("Ticket with id " + ticketId + " not found"));
	}

	@Override
	public List<Ticket> getAllBookedTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public void deleteTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);
	}

	@Override
	public Ticket updateTicket(Long ticketId, String newEmail) throws Exception {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		if (ticket.isPresent()) {
			Ticket updatedTicket = ticket.get();
			updatedTicket.setEmail(newEmail);
			return ticketRepository.save(updatedTicket);
		} else
			throw new TicketNotFoundException("Ticket with Id" + ticketId + " not found");
	}

	@Override
	public Ticket getTicketByEmail(String email) {
		return ticketRepository.findByEmail(email);
	}
}
