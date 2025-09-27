package com.pessoal.bora.api.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;

@Component
public class DriverAssembler implements RepresentationModelAssembler<Driver, DriverDTO> {

	@Autowired
	private final ModelMapper modelMapper;
	
	public DriverAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public DriverDTO toModel(Driver entity) {
		
		return modelMapper.map(entity, DriverDTO.class);

	}

}
