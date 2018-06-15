package com.camilasoares.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.repositories.ClientRepository;
import com.camilasoares.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client find(Integer id) {
		Client cli = clientRepository.findOne(id);
		if(cli == null) {
			throw new ObjectNotFoundException("Cliente não encontrado! Id: " + id
					+ ", Tipo: " + Client.class.getName());
		}
		return cli;
	}
}
