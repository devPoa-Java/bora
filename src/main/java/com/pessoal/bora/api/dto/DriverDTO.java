package com.pessoal.bora.api.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.pessoal.bora.api.domain.Driver;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;



public class DriverDTO extends RepresentationModel<DriverDTO> {

	private Long id;
	@Schema(description = "Nome do motorista")
	@Size(min = 5, max = 255)
	private String name;
	@Schema(description = "Data de nascimento do motorista")
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
