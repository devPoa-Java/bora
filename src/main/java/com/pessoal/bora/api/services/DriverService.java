package com.pessoal.bora.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;
import com.pessoal.bora.api.repositories.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	
	public List<DriverDTO> findAll(){
		List<Driver> list = driverRepository.findAll();
		return list.stream().map(x -> new DriverDTO(x)).toList();
	}
}
