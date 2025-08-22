package com.pessoal.bora.api.dto;

import java.util.Date;

import com.pessoal.bora.api.domain.Driver;

public class DriverDTO {
	
	private Long id;
	private String name;
	private Date birthDate;
	
	public DriverDTO() {
	}

	public DriverDTO(Driver entity) {
		id = entity.getId();
		name = entity.getName();
		birthDate = entity.getBirthDate();
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
	

}
