package com.camilasoares.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Endereco;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Endereco, Long> {

}
