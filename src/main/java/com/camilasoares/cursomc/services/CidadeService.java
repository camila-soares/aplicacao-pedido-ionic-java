package com.camilasoares.cursomc.services;

import com.camilasoares.cursomc.domain.Cidade;
import com.camilasoares.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;


    public List<Cidade> findByEstados(Integer estadoId){
        return cidadeRepository.findCidades ( estadoId );
    }
}
