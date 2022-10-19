package com.edugobeti.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.edugobeti.cursomc.DTO.ClientNewDTO;
import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.domain.enuns.ClientType;
import com.edugobeti.cursomc.repository.ClientRepository;
import com.edugobeti.cursomc.resource.exception.FieldMessage;
import com.edugobeti.cursomc.service.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	ClientRepository repo ;
	
	@Override
	public void initialize( ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getType().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpf_cnpj())){
			list.add(new FieldMessage("cpf_cnpj", "CPF Inválido"));
		}
		
		if(objDto.getType().equals(ClientType.PESSSOJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj", "CNPJ Inválido"));
		}
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
