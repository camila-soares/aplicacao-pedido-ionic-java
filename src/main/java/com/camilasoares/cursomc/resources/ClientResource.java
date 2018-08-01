package com.camilasoares.cursomc.resources;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.dto.ClientDTO;
import com.camilasoares.cursomc.services.ClientService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClientResource {
	/*
	 * Proteger contra serialização json ciclica
	 */
	private final ClientService clientService;

	@Autowired
	public ClientResource(ClientService clientService){
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> list = clientService.findAll();
		List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client cli = clientService.find(id);
		return ResponseEntity.ok().body(cli);
	}
	
//	@GetMapping
//	public ResponseEntity<Client> findByEmail(@PathVariable String email){
//		Client obj = clientService.findByEmail(email);
//		return ResponseEntity.ok().body ( obj );
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTO objDTO){
		Client obj = clientService.fromDTO(objDTO);
		obj = clientService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable Integer id) throws ObjectNotFoundException{
		Client obj = clientService.fromDTO(objDTO);
		obj.setId(null);
		obj = clientService.update(obj);
		return ResponseEntity.noContent().build();
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	

}
