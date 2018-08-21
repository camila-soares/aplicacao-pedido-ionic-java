package com.camilasoares.cursomc.repositories;

import com.camilasoares.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    //@Transactional
    //List <Estado> findAllByOrOrderByNome();
}
