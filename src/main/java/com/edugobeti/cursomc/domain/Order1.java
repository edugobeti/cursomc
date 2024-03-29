package com.edugobeti.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Order1 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instant;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "adressShipp_id")
	private Adress adressShipp;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> itens = new HashSet<>();
	
	public Order1() {
	}
	
	public Order1(Integer id, Date instant, Client client, Adress adressShipp) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.adressShipp = adressShipp;
	}
	
	public Double getOrderTotal() {
		Double total = 0.0;
		for (OrderItem ot : itens) {
			total = total + ot.getSubtotal();
		}
		return total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Adress getAdressShipp() {
		return adressShipp;
	}

	public void setAdresses(Adress adressPhipp) {
		this.adressShipp = adressPhipp;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItens() {
		return itens;
	}

	public void setItens(Set<OrderItem> itens) {
		this.itens = itens;
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
		Order1 other = (Order1) obj;
		return Objects.equals(id, other.id);
	}
	
}
