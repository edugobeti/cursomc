package com.edugobeti.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.edugobeti.cursomc.domain.enuns.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy =  InheritanceType.JOINED)
public abstract class Payment  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer status;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	private Order1 order;

	public Payment() {
	}
	
	public Payment(Integer id, PaymentStatus status, Order1 order) {
		super();
		this.id = id;
		this.status = status.getCod();
		this.order = order;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}
	public void setStatus(PaymentStatus status) {
		this.status = status.getCod();
	}
	public Order1 getOrder() {
		return order;
	}
	public void setOrder(Order1 order) {
		this.order = order;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
}
