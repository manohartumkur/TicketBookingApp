package com.fin.ticketapp.repository;

import com.fin.ticketapp.model.Ticket;
import com.fin.ticketapp.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

@DataJpaTest
@RunWith(SpringRunner.class)
public class TicketRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private TicketRepository ticketRepository;

	@Test
	void saveTicket() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");
		Ticket ticket = testEntityManager.persist(mockTicket);
		Optional<Ticket> ticketFromDB = ticketRepository.findById(ticket.getId());
		assertThat(ticketFromDB.get()).isEqualTo(ticket);

	}

	@Test
	void findTicketTest() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");

		Ticket ticket = testEntityManager.persist(mockTicket);
		Optional<Ticket> ticketFromDB = ticketRepository.findById(ticket.getId());
		assertThat(ticketFromDB.get()).isEqualTo(ticket);

	}

	@Test
	void findAllTicketsTest() throws Exception {

		Ticket mockTicket1 = new Ticket();
		mockTicket1.setPassengerName("Martin Bingel");
		mockTicket1.setSourceLocation("Kolkata");
		mockTicket1.setDestinationLocation("Delhi");
		mockTicket1.setBookingDate(new Date());
		mockTicket1.setEmail("martin.s2017@gmail.com");

		Ticket mockTicket2 = new Ticket();
		mockTicket2.setPassengerName("Sean Murphy");
		mockTicket2.setSourceLocation("Kolkata");
		mockTicket2.setDestinationLocation("Mumbai");
		mockTicket2.setBookingDate(new Date());
		mockTicket2.setEmail("sean.s2017@gmail.com");

		testEntityManager.persist(mockTicket1);
		testEntityManager.persist(mockTicket2);

		List<Ticket> ticketList = ticketRepository.findAll();

		assertThat(ticketList.size()).isEqualTo(2);
	}

	@Test
	void getTicketByEmailTest() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");

		String email = "martin.s2017@gmail.com";
		testEntityManager.persist(mockTicket);
		assertThat(ticketRepository.findByEmail(email)).isEqualTo(mockTicket);
	}

}