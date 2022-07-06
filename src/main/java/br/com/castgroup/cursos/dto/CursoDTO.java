package br.com.castgroup.cursos.dto;

import java.time.LocalDate;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.entities.Curso;

public class CursoDTO {

	private Integer idCurso;
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private Integer qtdAlunos;
	private Categoria categoria;

	public CursoDTO() {
		// TODO Auto-generated constructor stub
	}
	 public CursoDTO(Curso curso) {
		 this.idCurso = curso.getIdCurso();
		 this.descricao = curso.getDescricao();
		 this.dataInicio = curso.getDataInicio();
		 this.dataTermino = curso.getDataTermino();
		 this.qtdAlunos =  curso.getQtdAlunos();
		 this.categoria = curso.getCategoria();
	 }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getQtdAlunos() {
		return qtdAlunos;
	}

	public void setQtdAlunos(Integer qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
}
