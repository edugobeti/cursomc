package com.edugobeti.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edugobeti.cursomc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
