package com.pessoal.bora.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoal.bora.api.dto.TravelRequestDTO;
import com.pessoal.bora.api.dto.TravelResponseDTO;
import com.pessoal.bora.api.services.TravelRequestService;

@RestController
@RequestMapping(value = "/travelRequests", produces = "application/json")
public class TravelRequestController {
	
	@Autowired
	private TravelRequestService travelRequestService;
	
	
	@PostMapping
	public EntityModel<TravelResponseDTO> saveTravelRequest(@RequestBody TravelRequestDTO request) {
		TravelResponseDTO response = travelRequestService.saveTravelRequest(request);
		return travelRequestService.buildOutputModel(request, response);
		
		
	}

}
