	package com.edugobeti.cursomc.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResourse {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "O rest esta funcionando";
	}
}
