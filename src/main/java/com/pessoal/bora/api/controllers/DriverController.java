package com.pessoal.bora.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<DriverDTO> findAll(){
		List<DriverDTO> listDrivers = driverService.findAll();
		return listDrivers;
	}

}
