package com.edugobeti.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edugobeti.cursomc.DTO.CategoryDTO;
import com.edugobeti.cursomc.domain.Category;
import com.edugobeti.cursomc.repository.CategoryRepository;
import com.edugobeti.cursomc.service.exception.DataIntegratyException;
import com.edugobeti.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

	}
	
	public Category insert ( Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegratyException("Categoria com produto associado não pode ser deletada!");
		}
	}
	
	public List<Category> findAll() {
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer pages, Integer linesPerPages, String direct, String orderBy ){
		PageRequest pageRequest = PageRequest.of(pages, linesPerPages, Direction.valueOf(direct), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fronDTO (CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getName());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
