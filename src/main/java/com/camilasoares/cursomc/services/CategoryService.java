package com.camilasoares.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camilasoares.cursomc.domain.Category;
import com.camilasoares.cursomc.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category buscar(Integer id){
		Optional<Category> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
