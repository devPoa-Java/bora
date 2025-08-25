package com.pessoal.bora.api.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pessoal.bora.api.domain.Passenger;
import com.pessoal.bora.api.domain.TravelRequest;
import com.pessoal.bora.api.domain.TravelRequestStatus;
import com.pessoal.bora.api.dto.TravelRequestDTO;
import com.pessoal.bora.api.repositories.PassengerRepository;
import com.pessoal.bora.api.repositories.TravelRequestRepository;

@Service
public class TravelRequestService {
	
	@Autowired
	private TravelRequestRepository  travelRequestRepository;
	@Autowired PassengerRepository passengerRepository;
	
	
	
	@Transactional
	public void saveTravelRequest(TravelRequestDTO travelRequestDTO) {
		Passenger passenger = passengerRepository.findById(travelRequestDTO.getPassengerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		TravelRequest travelRequest = new TravelRequest();
		travelRequest.setPassenger(passenger);
		travelRequest.setOrigin(travelRequestDTO.getOrigin());
		travelRequest.setDestination(travelRequestDTO.getDestination());
		travelRequest.setStatus(TravelRequestStatus.CREATED);
		travelRequest.setCreationDate(Instant.now());
		travelRequestRepository.save(travelRequest);
		
	}
	

}
