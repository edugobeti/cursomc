package com.edugobeti.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugobeti.cursomc.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
