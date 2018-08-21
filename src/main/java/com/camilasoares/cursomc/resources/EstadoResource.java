package com.camilasoares.cursomc.resources;

import com.camilasoares.cursomc.domain.Cidade;
import com.camilasoares.cursomc.domain.Estado;
import com.camilasoares.cursomc.dto.CidadeDTO;
import com.camilasoares.cursomc.dto.EstadoDTO;
import com.camilasoares.cursomc.services.CidadeService;
import com.camilasoares.cursomc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = estadoService.findAll ();
        List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO (obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
        List<Cidade> list = cidadeService.findByEstados ( estadoId );
        List<CidadeDTO> listDto = list.stream ().map ( obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok ().body ( listDto );
    }

}
