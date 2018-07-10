package com.camilasoares.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Adress;

public interface AddressRepository extends JpaRepository<Adress, Long> {

}
