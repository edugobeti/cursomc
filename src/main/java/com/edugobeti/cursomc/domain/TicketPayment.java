package com.edugobeti.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edugobeti.cursomc.domain.enuns.PaymentStatus;

@Entity
public class TicketPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	public TicketPayment() {
		super();
	}
	public TicketPayment(Integer id, PaymentStatus status, Order1 order, Date dueDate, Date paymentDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}
