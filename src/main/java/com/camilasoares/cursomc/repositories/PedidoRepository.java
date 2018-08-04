package com.camilasoares.cursomc.repositories;

import com.camilasoares.cursomc.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.camilasoares.cursomc.domain.Pedido;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    Page<Pedido> findByClient(Client client, Pageable pageRequest);

}
