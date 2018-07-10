package com.camilasoares.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.camilasoares.cursomc.domain.Category;
import com.camilasoares.cursomc.domain.Pedido;
import com.camilasoares.cursomc.domain.Product;
import com.camilasoares.cursomc.repositories.CategoryRepository;
import com.camilasoares.cursomc.repositories.ProductRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	public Product find(Integer id) throws ObjectNotFoundException {
		Product product = productRepository.findOne(id);
		if(product == null) {
			throw new ObjectNotFoundException("Pedido não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return product;
	}
	
	public Page<Product> seach(String nome, List<Integer> ids, Integer page,Integer linesForpage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesForpage, Direction.valueOf(direction), orderBy);
		List<Category> categorias = categoryRepository.findAll(ids);
		return productRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	

}
