package com.fin.ticketapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fin.ticketapp.model.Ticket;
import com.fin.ticketapp.service.TicketBookingService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebMvcTest
@RunWith(SpringRunner.class)
class TicketControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicketBookingService ticketBookingService;

	@Before
	void setUp() {

	}

	@Test
	void saveTicket() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setId(1);
		mockTicket.setPassengerName("Martin Bingel");
		mockTicket.setSourceLocation("Kolkata");
		mockTicket.setDestinationLocation("Delhi");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("martin.s2017@gmail.com");

		String inputJson = this.mapToJson(mockTicket);
		String URI = "/tickets";
		Mockito.when(ticketBookingService.createTicket(Mockito.any(Ticket.class))).thenReturn(mockTicket);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).content(inputJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String outputJson = response.getContentAsString();
		Ticket ticket = this.maptoTicket(outputJson);
		assertEquals(1, ticket.getId());
		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(200, response.getStatus());
	}

	private Ticket maptoTicket(String outputJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(outputJson, Ticket.class);
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
		String inputJson = this.mapToJson(mockTicket);
		String URI = "/tickets/1";
		
		Mockito.when(ticketBookingService.getTicketById(Mockito.anyLong())).thenReturn(mockTicket);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).content(inputJson)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);

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
		String URI = "/tickets";
		Mockito.when(ticketBookingService.getAllBookedTickets()).thenReturn(ticketList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(ticketList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
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
		String inputJson = this.mapToJson(mockTicket);
		String URI = "/tickets/email/martin.s2017@gmail.com";

		Mockito.when(ticketBookingService.getTicketByEmail(Mockito.anyString())).thenReturn(mockTicket);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(inputJson);
	}
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}