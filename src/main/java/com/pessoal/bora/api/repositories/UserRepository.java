package com.pessoal.bora.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoal.bora.api.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
