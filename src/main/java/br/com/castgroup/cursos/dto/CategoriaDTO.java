package br.com.castgroup.cursos.dto;

import br.com.castgroup.cursos.entities.Categoria;

public class CategoriaDTO {
	

	private Integer idCategoria;
	private String categorias;
	
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}

	public CategoriaDTO(Categoria categoria) {
		this.idCategoria = categoria.getIdCategoria();
		this.categorias = categoria.getCategorias();
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	
	
}
