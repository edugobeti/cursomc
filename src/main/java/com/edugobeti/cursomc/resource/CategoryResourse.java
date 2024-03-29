package com.edugobeti.cursomc.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edugobeti.cursomc.DTO.CategoryDTO;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDTO){
		Category obj = service.fronDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = ("/{id}") , method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody CategoryDTO objDTO, @PathVariable Integer id){
		Category obj = service.fronDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<Category> list = service.findAll();
		List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
 		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value = "pages", defaultValue = "0") Integer pages,
			@RequestParam(value = "linesPages", defaultValue = "24") Integer linesPerPages,
			@RequestParam(value = "direction", defaultValue = "ASC") String direct,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
		Page<Category> list = service.findPage(pages, linesPerPages, direct, orderBy);
	    Page<CategoryDTO> listDTO = list.map(obj -> new CategoryDTO(obj));
 		return ResponseEntity.ok().body(listDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

