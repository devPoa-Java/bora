package com.pessoal.bora.api.dto;

import com.pessoal.bora.api.domain.Passenger;

public class PassengerDTO {
	
	private Long id;
	private String name;
	
	public PassengerDTO() {
	}
	
	public PassengerDTO(Passenger entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
