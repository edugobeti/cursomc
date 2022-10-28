package com.edugobeti.cursomc.domain;

import javax.persistence.Entity;

import com.edugobeti.cursomc.domain.enuns.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("creditPayment")
public class CreditPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numberInstallments;

	public CreditPayment() {
		super();
	}

	public CreditPayment(Integer id, PaymentStatus status, Order1 order, Integer numberInstallments) {
		super(id, status, order);
		this.numberInstallments = numberInstallments;
	}

	public Integer getNumberInstallments() {
		return numberInstallments;
	}

	public void setNumberInstallments(Integer numberInstallments) {
		this.numberInstallments = numberInstallments;
	}
	
	
	
	

	

}
