package com.pessoal.bora.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoal.bora.api.domain.TravelRequest;
import com.pessoal.bora.api.domain.TravelRequestStatus;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long> {
	
	
	public List<TravelRequest> findByStatus(TravelRequestStatus status);

}
