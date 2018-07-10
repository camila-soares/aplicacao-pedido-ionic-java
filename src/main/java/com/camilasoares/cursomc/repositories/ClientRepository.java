package com.camilasoares.cursomc.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Transactional
	Client findByEmail(String email);
}
