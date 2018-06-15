package com.camilasoares.cursomc.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.services.ClientService;

@RestController
@RequestMapping(value="/clientes")
public class ClientResources {
	/*
	 * Proteger contra serialização json ciclica
	 */
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client cli = clientService.find(id);
		return ResponseEntity.ok().body(cli);
	}

}
