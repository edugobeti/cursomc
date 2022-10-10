package com.edugobeti.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugobeti.cursomc.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
