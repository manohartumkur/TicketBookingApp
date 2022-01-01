package com.fin.ticketapp.service;

import com.fin.ticketapp.model.Ticket;
import com.fin.ticketapp.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceTest {

	@Autowired
	private TicketBookingService ticketBookingService;

	@MockBean
	private TicketRepository ticketRepository;

	@Test
	void saveTicket() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setId(1);
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");
		Mockito.when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(mockTicket);
		assertThat(ticketBookingService.createTicket(mockTicket)).isEqualTo(mockTicket);

	}

	@Test
	void findTicketTest() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setId(1);
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");

		Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(mockTicket));

		assertThat(ticketBookingService.getTicketById(1L)).isEqualTo(mockTicket);

	}

	@Test
	void findAllTicketsTest() throws Exception {

		Ticket mockTicket1 = new Ticket();
		mockTicket1.setId(1);
		mockTicket1.setPassengerName("Martin Bingel");
		mockTicket1.setSourceLocation("Kolkata");
		mockTicket1.setDestinationLocation("Delhi");
		mockTicket1.setBookingDate(new Date());
		mockTicket1.setEmail("martin.s2017@gmail.com");

		Ticket mockTicket2 = new Ticket();
		mockTicket2.setId(2);
		mockTicket2.setPassengerName("Sean Murphy");
		mockTicket2.setSourceLocation("Kolkata");
		mockTicket2.setDestinationLocation("Mumbai");
		mockTicket2.setBookingDate(new Date());
		mockTicket2.setEmail("sean.s2017@gmail.com");

		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(mockTicket1);
		ticketList.add(mockTicket2);

		Mockito.when(ticketRepository.findAll()).thenReturn(ticketList);
		assertThat(ticketBookingService.getAllBookedTickets().size()).isEqualTo(2);
	}

	@Test
	void getTicketByEmailTest() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setId(1);
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");

		String email = "martin.s2017@gmail.com";

		Mockito.when(ticketRepository.findByEmail(Mockito.anyString())).thenReturn(mockTicket);

		assertThat(ticketBookingService.getTicketByEmail(email)).isEqualTo(mockTicket);
	}

}