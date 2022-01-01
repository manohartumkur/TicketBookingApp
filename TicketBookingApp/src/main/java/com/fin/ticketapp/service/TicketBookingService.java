package com.fin.ticketapp.service;

import java.util.List;

import com.fin.ticketapp.model.Ticket;

public interface TicketBookingService {

	public Ticket createTicket(Ticket ticket);

	public Ticket getTicketById(Long ticketId) throws Exception;

	public List<Ticket> getAllBookedTickets();

	public void deleteTicket(Long ticketId);

	public Ticket updateTicket(Long ticketId, String newEmail) throws Exception;

	public Ticket getTicketByEmail(String email);
}
