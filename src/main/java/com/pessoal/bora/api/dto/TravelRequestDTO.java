package com.pessoal.bora.api.dto;

import com.pessoal.bora.api.domain.TravelRequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TravelRequestDTO {

	private Long id;
	@NotNull(message = "O campo passengerId não pode ser nulo")
	private Long passengerId;
	@NotEmpty(message = "O campo origin não pode estar em branco")
	private String origin;
	@NotEmpty(message = "O campo destination não pode estar em branco")
	private String destination;

	public TravelRequestDTO() {
	}

	public TravelRequestDTO(TravelRequest entity) {
		id = entity.getId();
		passengerId = entity.getPassenger().getId();
		origin = entity.getOrigin();
		destination = entity.getDestination();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
