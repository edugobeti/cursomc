package com.edugobeti.cursomc.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.edugobeti.cursomc.domain.Client;
import com.edugobeti.cursomc.service.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "Nome deve conter entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	public ClientDTO() {
	}
	
	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
