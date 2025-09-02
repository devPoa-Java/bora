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

import com.pessoal.bora.api.dto.PassengerDTO;
import com.pessoal.bora.api.services.PassengerService;

@RestController
@RequestMapping(value = "/passengers", produces = "application/json")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;

	@GetMapping
	public List<PassengerDTO> findAll() {
		return passengerService.findAll();
	}

	@GetMapping(value = "/{id}")
	public PassengerDTO findById(@PathVariable Long id) {
		return passengerService.findById(id);
	}

	@PostMapping
	public PassengerDTO savePassenger(@RequestBody PassengerDTO passengerDTO) {
		PassengerDTO passenger = passengerService.savePassenger(passengerDTO);
		return passenger;
	}

	@PatchMapping(value = "/{id}")
	public void updatePassenger(@PathVariable Long id, @RequestBody PassengerDTO passengerDTO) {
		PassengerDTO passenger = passengerService.findById(id);
		passenger.setName(passengerDTO.getName());
		passengerService.updatePassenger(id, passenger);

	}
	@DeleteMapping(value = "/{id}")
	public void deletePassenger(@PathVariable Long id) {
		passengerService.deletPassenger(id);
	}

}
