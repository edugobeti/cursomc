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

import com.edugobeti.cursomc.DTO.ClientDTO;
import com.edugobeti.cursomc.DTO.ClientNewDTO;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO){
		Client obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = ("/{id}") , method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody ClientDTO objDTO, @PathVariable Integer id){
		Client obj = service.fronDTO(objDTO);
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
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> list = service.findAll();
		List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
 		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "pages", defaultValue = "0") Integer pages,
			@RequestParam(value = "linesPages", defaultValue = "24") Integer linesPerPages,
			@RequestParam(value = "direction", defaultValue = "ASC") String direct,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
		Page<Client> list = service.findPage(pages, linesPerPages, direct, orderBy);
	    Page<ClientDTO> listDTO = list.map(obj -> new ClientDTO(obj));
 		return ResponseEntity.ok().body(listDTO);
	}
}
