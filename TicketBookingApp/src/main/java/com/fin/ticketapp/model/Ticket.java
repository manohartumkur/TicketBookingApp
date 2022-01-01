package com.fin.ticketapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ticket_id")
	private long id;

	@Column(name = "passenger_name", nullable = false)
	private String passengerName;
	@Column(name = "booking_date", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bookingDate;
	@Column(name = "source_location", nullable = false)
	private String sourceLocation;
	@Column(name = "destination_location", nullable = false)
	private String destinationLocation;
	@Column(name = "email", unique = true)
	private String email;
}
