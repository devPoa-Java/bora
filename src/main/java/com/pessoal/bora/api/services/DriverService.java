package com.pessoal.bora.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;
import com.pessoal.bora.api.repositories.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;
	
	@Transactional(readOnly = true)
	public List<DriverDTO> findAll(){
		List<Driver> list = driverRepository.findAll();
		return list.stream().map(x -> new DriverDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public DriverDTO findById(Long id) {
		Driver driver =  driverRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	    return  new DriverDTO(driver);
	}
}
