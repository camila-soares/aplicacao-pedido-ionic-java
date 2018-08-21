package com.camilasoares.cursomc.services;



import com.camilasoares.cursomc.domain.Category;
import com.camilasoares.cursomc.dto.CategoryDTO;
import com.camilasoares.cursomc.repositories.CategoryRepository;
import com.camilasoares.cursomc.services.exception.DataIntegrityException;
import com.camilasoares.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

	@Autowired
	private  CategoryRepository categoryRepository;


	public Category find(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}
	
	
	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}

	public Category update(Category obj) throws ObjectNotFoundException {
		Category newObj  = find(obj.getId());
		updateData(newObj, obj);
		return categoryRepository.save(newObj);
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setNome(obj.getNome());

	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try{
			categoryRepository.deleteById (id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel uma Categoria que possiu produto");
		}

	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Page<Category> findPage(Integer page,Integer linesForpage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesForpage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}

	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getNome());
	}
}
