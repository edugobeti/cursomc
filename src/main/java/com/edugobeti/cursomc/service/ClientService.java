package com.edugobeti.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugobeti.cursomc.DTO.ClientDTO;
import com.edugobeti.cursomc.DTO.ClientNewDTO;
import com.edugobeti.cursomc.domain.Adress;
import com.edugobeti.cursomc.domain.City;
import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.domain.enuns.ClientType;
import com.edugobeti.cursomc.repository.AdressRepository;
import com.edugobeti.cursomc.repository.ClientRepository;
import com.edugobeti.cursomc.service.exception.DataIntegratyException;
import com.edugobeti.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AdressRepository adressRepo;	
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert ( Client obj) {
		obj.setId(null);
		repo.save(obj);
		adressRepo.saveAll(obj.getAdresses());
		return obj;
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
	
	public Client fronDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(),objDTO.getEmail() , null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null,
				objDTO.getName(),
				objDTO.getEmail(),
				objDTO.getCpf_cnpj(),
				ClientType.toEnum(objDTO.getType()));
		City ct = new City(objDTO.getCityId(), null, null);
		Adress a1 = new Adress(null,
				objDTO.getPlace(),
				objDTO.getNumber(),
				objDTO.getComplement(),
				objDTO.getDistrict(),
				objDTO.getZipcod(),
				cli,ct);
		cli.getAdresses().add(a1);
		cli.getPhone().add(objDTO.getPhone1());
		if(objDTO.getPhone2() != null) {
			cli.getPhone().add(objDTO.getPhone2());
		}
		if(objDTO.getPhone3() != null) {
			cli.getPhone().add(objDTO.getPhone3());
		}
		return cli;
	}
	
	private void updateData (Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
			
	
}
