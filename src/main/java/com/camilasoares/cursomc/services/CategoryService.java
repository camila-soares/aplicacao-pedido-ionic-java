package com.camilasoares.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camilasoares.cursomc.domain.Category;
import com.camilasoares.cursomc.repositories.CategoryRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) throws ObjectNotFoundException{
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.camilasoares.cursomc.services.exception.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
				
	}
}
