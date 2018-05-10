package com.camilasoares.cursomc.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
