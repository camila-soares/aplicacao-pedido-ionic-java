package com.camilasoares.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.camilasoares.cursomc.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser no minimo 5 e no máximo 80 caracteres")
	private String nome;
	
	public CategoryDTO(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {this.id = id;	}

	public String getNome() {	return nome;}

	public void setNome(String nome) {	this.nome = nome;}
	
	public CategoryDTO(Category obj){
		id = obj.getId();
		nome = obj.getNome();
	}

}
