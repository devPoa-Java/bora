package com.pessoal.bora.api.dto;

import java.time.Instant;

import com.pessoal.bora.api.domain.TravelRequest;
import com.pessoal.bora.api.domain.TravelRequestStatus;

public class TravelResponseDTO {

	private Long id;
	private String origin;
	private String destination;

	private TravelRequestStatus status;
	private Instant creationDate;
	
	public TravelResponseDTO() {
	}
	
	public TravelResponseDTO(TravelRequest entity) {
		id = entity.getId();
		origin = entity.getOrigin();
		destination = entity.getDestination();
		status = entity.getStatus();
		creationDate = entity.getCreationDate();
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
	
	 

}
