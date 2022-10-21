package com.edugobeti.cursomc.domain;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem {

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Double off;
	private Integer qtt;
	private Double price;
		
	public OrderItem() {
	}

	public OrderItem(Order1 order, Product product, Double off, Integer qtt, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.off = off;
		this.qtt = qtt;
		this.price = price;
	}

	public Double getSubtotal() {
		return (price - off) * qtt;
	}
	
	@JsonIgnore
	public Order1 getOrder() {
		return id.getOrder();
	}
	
	public Product getProduct() {
		return id.getProduct()
;	}
	
	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public Double getOff() {
		return off;
	}

	public void setOff(Double off) {
		this.off = off;
	}

	public Integer getQtt() {
		return qtt;
	}

	public void setQtt(Integer qtt) {
		this.qtt = qtt;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	} 
	
}
