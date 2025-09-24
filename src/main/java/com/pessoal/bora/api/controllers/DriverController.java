package com.pessoal.bora.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class DriverController implements DriverSwagger {

	@Autowired
	private DriverService driverService;

	@GetMapping
	public Page<DriverDTO> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		
		Page<DriverDTO> driverPage = driverService.findAll(pageable);
		
		return driverPage;
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
	public void updateDriver(@PathVariable Long id, @RequestBody DriverDTO driverDto) {
		DriverDTO driver = driverService.findById(id);
		driver.setName(driverDto.getName());
		driver.setBirthDate(driverDto.getBirthDate());
		driverService.updateDriver(id, driver);

	}

}
