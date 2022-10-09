package com.edugobeti.cursomc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResourse {
	
	@Autowired
	private ClientService service;

	@RequestMapping(value=("/{id}"), method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
