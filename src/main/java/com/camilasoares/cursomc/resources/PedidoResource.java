package com.camilasoares.cursomc.resources;



import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.services.PedidoService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

//	@Bean
//	public PedidoService pedidoService(){
//		return new PedidoService ();
//	}

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Pedido obj = pedidoService.find ( id );
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = pedidoService.insert (obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Pedido> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}