package com.camilasoares.cursomc.resources;

import com.camilasoares.cursomc.domain.Client;
import com.camilasoares.cursomc.dto.ClientDTO;
import com.camilasoares.cursomc.dto.ClientNewDTO;
import com.camilasoares.cursomc.services.ClientService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.processing.FilerException;
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

	@PreAuthorize ( "hasAnyRole('ADMIN')" )
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
	

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO){
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
		obj.setId(id);
		obj = clientService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/email")
	public ResponseEntity<ClientDTO> findByEmail(@RequestParam(value="value") String email){
		Client obj = clientService.findByEMail(email);
		return ResponseEntity.ok().body ( new ClientDTO(obj) );
	}

	@PreAuthorize ( "hasAnyRole('ADMIN')" )
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
    @PreAuthorize ( "hasAnyRole('ADMIN')" )
	@GetMapping("/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage" , defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Client> list = clientService.findPage ( page, linesPerPage, orderBy, direction );
		Page<ClientDTO> listDTO = list.map ( obj -> new ClientDTO ( obj ) );
		return ResponseEntity.ok ().body ( listDTO );
	 }


	@PostMapping("/picture")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) throws FilerException {
		URI uri = clientService.uploadProfilePicture ( file );
		return ResponseEntity.created(uri).build();
	}

}
