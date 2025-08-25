package com.pessoal.bora.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoal.bora.api.domain.TravelRequest;

public interface TravelRequestRepository extends JpaRepository<TravelRequest, Long> {

}
