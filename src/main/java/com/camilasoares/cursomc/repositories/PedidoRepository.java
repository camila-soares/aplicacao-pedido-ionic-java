package com.camilasoares.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


}
