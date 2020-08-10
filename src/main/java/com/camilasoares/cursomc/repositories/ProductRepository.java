package com.camilasoares.cursomc.repositories;


import com.camilasoares.cursomc.domain.Category;
import com.camilasoares.cursomc.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	//@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")

	@Transactional
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categorias cat where obj.nome like %:nome% AND cat in :categorias")
	Page<Product> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome,@Param("categorias") List<Category> categorias, Pageable pageable);

}
