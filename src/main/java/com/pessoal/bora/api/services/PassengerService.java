package com.pessoal.bora.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pessoal.bora.api.domain.Passenger;
import com.pessoal.bora.api.dto.PassengerDTO;
import com.pessoal.bora.api.repositories.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	@Transactional(readOnly = true)
	public List<PassengerDTO> findAll() {
		List<Passenger> listPassenger = passengerRepository.findAll();
		return listPassenger.stream().map(p -> new PassengerDTO(p)).toList();
	}

	@Transactional(readOnly = true)
	public PassengerDTO findById(Long id) {
		Passenger passenger = passengerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new PassengerDTO(passenger);

	}
	
	@Transactional
	public PassengerDTO savePassenger(PassengerDTO passengerDto) {
		Passenger passenger = new Passenger();
		passenger.setName(passengerDto.getName());
		passengerRepository.save(passenger);
		return new PassengerDTO(passenger);
		
	}
	
	@Transactional
	public void updatePassenger(Long id, PassengerDTO passengerDto) {
		 Passenger passenger = passengerRepository.findById(id).get();
		 passenger.setName(Optional.ofNullable(passengerDto.getName()).orElse(passenger.getName()));
		 passengerRepository.save(passenger);
		 
	}
	
	@Transactional
	public void deletPassenger(Long id) {
		Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		passengerRepository.delete(passenger);
	
	}
	
	

}
