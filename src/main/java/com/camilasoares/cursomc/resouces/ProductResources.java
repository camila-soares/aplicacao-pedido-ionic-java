package com.camilasoares.cursomc.resouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camilasoares.cursomc.domain.Product;
import com.camilasoares.cursomc.dto.ProductDTO;
import com.camilasoares.cursomc.resouces.utils.URL;
import com.camilasoares.cursomc.services.ProductService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/produtos")
public class ProductResources {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Product prod = productService.find(id);
		return ResponseEntity.ok().body(prod);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="nome", defaultValue="")  String nome,
			@RequestParam(value="categorias", defaultValue="")  String categorias,
			@RequestParam(value="page", defaultValue="0")  Integer page,
			@RequestParam(value="linesForpage", defaultValue="24") Integer linesForpage,
			@RequestParam(value="orderBy", defaultValue="nome")  String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")  String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Product> list = productService.seach(nomeDecoded, ids, page, linesForpage, orderBy, direction);
		Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTO objDTO){
//		Product obj = productService.fromDTO(objDTO);
//		obj = productService.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(obj.getId())
//				.toUri();
//		return ResponseEntity.created(uri).build();
//	}
//	
//	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
//	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable Integer id) throws ObjectNotFoundException{
//		Product obj = productService.fromDTO(objDTO);
//		obj.setId(null);
//		obj = productService.update(obj);
//		return ResponseEntity.noContent().build();
//		
//	}
//	
//
//	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
//		productService.delete(id);
//		return ResponseEntity.noContent().build();
//		
//	}

}
