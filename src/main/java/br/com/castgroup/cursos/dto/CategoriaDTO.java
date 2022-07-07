package br.com.castgroup.cursos.dto;

import br.com.castgroup.cursos.entities.Categoria;

public class CategoriaDTO {
	

	private Integer idCategoria;
	private String categoria;
	
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}

	public CategoriaDTO(Categoria categoria) {
		this.idCategoria = categoria.getIdCategoria();
		this.categoria = categoria.getCategoria();
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
	}
	

	
	
}
