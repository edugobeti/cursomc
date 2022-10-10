package com.edugobeti.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugobeti.cursomc.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
