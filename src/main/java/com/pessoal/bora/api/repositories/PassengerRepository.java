package com.pessoal.bora.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoal.bora.api.domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
