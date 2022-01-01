package com.fin.ticketapp.repository;

import com.fin.ticketapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	Ticket findByEmail(String email);
}
