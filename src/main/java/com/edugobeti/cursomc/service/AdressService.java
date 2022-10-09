package com.edugobeti.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edugobeti.cursomc.domain.Adress;
import com.edugobeti.cursomc.repository.AdressRepository;
import com.edugobeti.cursomc.service.exception.ObjectNotFoundException;

@Service
public class AdressService {

	@Autowired
	private AdressRepository repo;
	
	public Adress find(Integer id) {
		Optional<Adress> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Adress.class.getName()));

	}
}
