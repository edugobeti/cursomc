package com.edugobeti.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edugobeti.cursomc.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>{

}
