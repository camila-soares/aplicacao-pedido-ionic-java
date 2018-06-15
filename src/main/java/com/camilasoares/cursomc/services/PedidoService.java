package com.camilasoares.cursomc.services;



import org.springframework.beans.factory.annotation.Autowired;

import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Long id) throws ObjectNotFoundException {
		Pedido pedido = repo.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Pedido não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return pedido;
	}

}
