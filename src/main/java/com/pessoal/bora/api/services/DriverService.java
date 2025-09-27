package com.pessoal.bora.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pessoal.bora.api.domain.Driver;
import com.pessoal.bora.api.dto.DriverDTO;
import com.pessoal.bora.api.repositories.DriverRepository;

@Service
public class DriverService {
	
	private static final int PAGE_SIZE = 20;

	@Autowired
	private DriverRepository driverRepository;

	@Transactional(readOnly = true)
	public Page<Driver> findAll(Pageable pageable) {
		if(pageable.getPageSize() > PAGE_SIZE) {
			pageable = PageRequest.of(pageable.getPageNumber(), PAGE_SIZE);
		}
		
		return driverRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public DriverDTO findById(Long id) {
		Driver driver = driverRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new DriverDTO(driver);
	}
	@Transactional
	public void delete(Long id) {
		Driver driver = driverRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		driverRepository.delete(driver);
	}

	@Transactional
	public void saveDriver(DriverDTO driverDto) {
		Driver driver = new Driver();
		driver.setName(driverDto.getName());
		driver.setBirthDate(driverDto.getBirthDate());
		driverRepository.save(driver);

	}

	@Transactional
	public void updateDriver(Long id, DriverDTO driverDto) {
		Driver driver = driverRepository.findById(id).get();
		driver.setName(Optional.ofNullable(driverDto.getName()).orElse(driver.getName()));
		driver.setBirthDate(Optional.ofNullable(driverDto.getBirthDate()).orElse(driver.getBirthDate()));
		driverRepository.save(driver);

	}

}
