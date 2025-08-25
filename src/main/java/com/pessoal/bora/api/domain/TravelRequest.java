package com.pessoal.bora.api.domain;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_travelRequest")
public class TravelRequest {
	@ManyToOne
	private Passenger passenger;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String origin;
	private String destination;

	@Enumerated(EnumType.STRING)
	private TravelRequestStatus status;
	private Instant creationDate;

	public TravelRequest() {
	}

	public TravelRequest(Passenger passenger, Long id, String origin, String destination, TravelRequestStatus status,
			Instant creationDate) {
		this.passenger = passenger;
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.status = status;
		this.creationDate = creationDate;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public TravelRequestStatus getStatus() {
		return status;
	}

	public void setStatus(TravelRequestStatus status) {
		this.status = status;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TravelRequest other = (TravelRequest) obj;
		return Objects.equals(id, other.id);
	}

}
