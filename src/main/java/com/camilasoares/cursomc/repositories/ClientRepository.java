package com.camilasoares.cursomc.repositories;


import com.camilasoares.cursomc.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Transactional(readOnly=true)
	Client findByEmail(String email);

	@Transactional(readOnly=true)
	Client findByCpfOuCnpj(String cpfOuCnpj);

    Client findAllById(Integer id);
}
