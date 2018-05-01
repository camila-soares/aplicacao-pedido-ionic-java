package com.camilasoares.cursomc.resouces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camilasoares.cursomc.domain.Category;
import com.camilasoares.cursomc.services.CategoryService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryResources {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

}
