package com.camilasoares.cursomc.repositories;

import com.camilasoares.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    //@Transactional(readOnly=true)
   // public List<Estado> findAllByOrOrderByNome();
}
