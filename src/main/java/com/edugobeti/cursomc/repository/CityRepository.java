package com.edugobeti.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugobeti.cursomc.domain.City;

public interface CityRepository  extends JpaRepository<City, Integer>{

}
