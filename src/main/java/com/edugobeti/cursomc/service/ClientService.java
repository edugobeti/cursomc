package com.edugobeti.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edugobeti.cursomc.DTO.ClientDTO;
import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.repository.ClientRepository;
import com.edugobeti.cursomc.service.exception.DataIntegratyException;
import com.edugobeti.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegratyException("Não é possível excluir porque há entidades relacionadas!");
		}
	}
	
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer pages, Integer linesPerPages, String direct, String orderBy ){
		PageRequest pageRequest = PageRequest.of(pages, linesPerPages, Direction.valueOf(direct), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fronDTO (ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(),objDTO.getEmail() , null, null);
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
			
	
}
