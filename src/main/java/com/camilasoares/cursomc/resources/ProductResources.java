package com.camilasoares.cursomc.resources;

import com.camilasoares.cursomc.domain.Product;
import com.camilasoares.cursomc.dto.ProductDTO;
import com.camilasoares.cursomc.resources.utils.URL;
import com.camilasoares.cursomc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductResources {
	
	@Autowired
	private ProductService productService;


	
	@GetMapping("/{id}")
	public ResponseEntity<Product> find(@PathVariable Integer id)  {
		Product prod = productService.find(id);
		return ResponseEntity.ok().body(prod);
	}
	
	@GetMapping
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
	
	


}
