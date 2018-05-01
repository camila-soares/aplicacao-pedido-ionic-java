package com.camilasoares.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
