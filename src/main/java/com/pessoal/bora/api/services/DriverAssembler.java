package com.pessoal.bora.api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pessoal.bora.api.controllers.DriverController;
import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;

@Component
public class DriverAssembler implements RepresentationModelAssembler<Driver, DriverDTO> {

	@Override
	public DriverDTO toModel(Driver entity) {
		
			DriverDTO dto = new DriverDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setBirthDate(entity.getBirthDate());
			dto.add(linkTo(methodOn(DriverController.class).findById(entity.getId())).withRel("lastPage"));
		return dto;
	}

}
