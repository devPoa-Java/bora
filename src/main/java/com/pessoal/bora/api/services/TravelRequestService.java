package com.pessoal.bora.api.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pessoal.bora.api.controllers.PassengerController;
import com.pessoal.bora.api.domain.Passenger;
import com.pessoal.bora.api.domain.TravelRequest;
import com.pessoal.bora.api.domain.TravelRequestStatus;
import com.pessoal.bora.api.dto.TravelRequestDTO;
import com.pessoal.bora.api.dto.TravelResponseDTO;
import com.pessoal.bora.api.repositories.PassengerRepository;
import com.pessoal.bora.api.repositories.TravelRequestRepository;

@Service
public class TravelRequestService {

	@Autowired
	private TravelRequestRepository travelRequestRepository;
	@Autowired
	PassengerRepository passengerRepository;

	@Transactional
	public TravelResponseDTO saveTravelRequest(TravelRequestDTO travelRequestDTO) {
		Passenger passenger = passengerRepository.findById(travelRequestDTO.getPassengerId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		TravelRequest travelRequest = new TravelRequest();
		travelRequest.setPassenger(passenger);
		travelRequest.setOrigin(travelRequestDTO.getOrigin());
		travelRequest.setDestination(travelRequestDTO.getDestination());
		travelRequest.setStatus(TravelRequestStatus.CREATED);
		travelRequest.setCreationDate(Instant.now());
		travelRequestRepository.save(travelRequest);
		TravelResponseDTO response = new TravelResponseDTO();
		response.setId(travelRequest.getId());
		response.setOrigin(travelRequest.getOrigin());
		response.setDestination(travelRequest.getDestination());
		response.setStatus(travelRequest.getStatus());
		response.setCreationDate(travelRequest.getCreationDate());
		
		return response; 

	}

	public EntityModel<TravelResponseDTO> buildOutputModel(TravelRequestDTO request, TravelResponseDTO response) {
		Passenger passenger = passengerRepository.findById(request.getPassengerId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		
		EntityModel<TravelResponseDTO> model = EntityModel.of(response);
		Link passengerLink = WebMvcLinkBuilder
											.linkTo(PassengerController.class)
											.slash(request.getPassengerId())
											.withRel("passenger")
											.withTitle(passenger.getName());
											model.add(passengerLink);

		return model;
	}

}
