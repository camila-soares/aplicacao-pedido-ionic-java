package com.camilasoares.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Endereco;

public interface AdressRepository extends JpaRepository<Endereco, Integer>{

}
