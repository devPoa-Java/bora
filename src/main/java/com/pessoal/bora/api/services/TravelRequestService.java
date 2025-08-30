package com.pessoal.bora.api.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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

	private static final int MAX_TRAVEL_TIME = 600;

	@Autowired
	private GMapsService gMapService;

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

	@Transactional
	public List<TravelRequestDTO> listNearbyTravelRequests(String currentAddress) {
		List<TravelRequest> requests = travelRequestRepository.findByStatus(TravelRequestStatus.CREATED);
		return requests.stream()
				.filter(tr -> gMapService.getDistanceBetweenAddresses(currentAddress, tr.getOrigin()) < MAX_TRAVEL_TIME)
				.map(tr -> new TravelRequestDTO(tr)).collect(Collectors.toList());
	}
	
	@Transactional
	public List<EntityModel<TravelResponseDTO>> buildOutputModel(List<TravelRequestDTO> requests){
		 return requests.stream().map(tr -> buildOutputModel(tr, mapper(tr))).collect(Collectors.toList());
		 
	
	}
	
	
	
	private TravelResponseDTO mapper(TravelRequestDTO travelRequestDTO) {
		
		Passenger passenger = passengerRepository.findById(travelRequestDTO.getPassengerId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		TravelRequest travelRequest = new TravelRequest();
		travelRequest.setId(travelRequestDTO.getId());
		travelRequest.setPassenger(passenger);
		travelRequest.setOrigin(travelRequestDTO.getOrigin());
		travelRequest.setDestination(travelRequestDTO.getDestination());
		travelRequest.setStatus(TravelRequestStatus.CREATED);
		travelRequest.setCreationDate(Instant.now());
		
		TravelResponseDTO response = new TravelResponseDTO();
		response.setId(travelRequest.getId());
		response.setOrigin(travelRequest.getOrigin());
		response.setDestination(travelRequest.getDestination());
		response.setStatus(travelRequest.getStatus());
		response.setCreationDate(travelRequest.getCreationDate());

		return response;

	}
	

	
	
	
	
	
	public EntityModel<TravelResponseDTO> buildOutputModel(TravelRequestDTO request, TravelResponseDTO response){
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
