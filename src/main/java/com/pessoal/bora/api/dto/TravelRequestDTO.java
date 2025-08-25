package com.pessoal.bora.api.dto;

import com.pessoal.bora.api.domain.TravelRequest;

public class TravelRequestDTO {
	
	private Long passengerId;
	private String origin;
	private String destination;
	
	
	public TravelRequestDTO() {
	}
	
	public TravelRequestDTO(TravelRequest entity) {
		passengerId = entity.getPassenger().getId();
		origin = entity.getOrigin();
		destination = entity.getDestination();
		
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
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
	
	

}
