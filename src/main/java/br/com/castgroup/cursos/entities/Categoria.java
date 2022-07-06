package br.com.castgroup.cursos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	private String categorias;
	
	@OneToMany(mappedBy = "categoria")
	private List<Curso> curso = new ArrayList<>();

	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(Integer idCategoria, String categorias, List<Curso> curso) {
		super();
		this.idCategoria = idCategoria;
		this.categorias = categorias;
		this.curso = curso;
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

	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}
	
	

}
