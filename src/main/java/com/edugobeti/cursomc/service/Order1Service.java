package com.edugobeti.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edugobeti.cursomc.domain.Order1;
import com.edugobeti.cursomc.repository.Order1Repository;
import com.edugobeti.cursomc.service.exception.ObjectNotFoundException;

@Service
public class Order1Service {

	@Autowired
	private Order1Repository repo;
	
	public Order1 find(Integer id) {
		Optional<Order1> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Order1.class.getName()));

	}
}
