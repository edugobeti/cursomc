package com.edugobeti.cursomc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edugobeti.cursomc.domain.Category;
import com.edugobeti.cursomc.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResourse {
	
	@Autowired
	private CategoryService service;

	@RequestMapping(value=("/{id}"), method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
