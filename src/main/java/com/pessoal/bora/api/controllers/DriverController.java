package com.pessoal.bora.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoal.bora.api.dto.DriverDTO;
import com.pessoal.bora.api.services.DriverService;

@RestController
@RequestMapping(value = "/drivers", produces = "application/json")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@GetMapping
	public List<DriverDTO> findAll() {
		List<DriverDTO> listDrivers = driverService.findAll();
		return listDrivers;
	}

	@GetMapping(value = "/{id}")
	public DriverDTO findById(@PathVariable Long id) {
		return driverService.findById(id);
	}
	@DeleteMapping(value = "/{id}")
	public void delet(@PathVariable Long id) {
		driverService.delete(id);
	}

	@PostMapping
	public void saveDriver(@RequestBody DriverDTO driver) {
		driverService.saveDriver(driver);
	}
	
	@PatchMapping(value = "/{id}")
	public DriverDTO updateDriver( @PathVariable Long id, @RequestBody DriverDTO driverDto) {
		DriverDTO driver = driverService.findById(id);
		if(driverDto.getName() != null) {
		driver.setName(driverDto.getName());
		}
		if(driverDto.getBirthDate() != null) {
		driver.setBirthDate(driverDto.getBirthDate());
		}
		driverService.updateDriver(id,driver);
		return driver;
		
		
	}

	

}
