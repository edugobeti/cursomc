package com.edugobeti.cursomc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edugobeti.cursomc.domain.Order1;
import com.edugobeti.cursomc.service.Order1Service;

@RestController
@RequestMapping(value = "/orders")
public class Order1Resourse {
	
	@Autowired
	private Order1Service service;

	@RequestMapping(value=("/{id}"), method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Order1 obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
