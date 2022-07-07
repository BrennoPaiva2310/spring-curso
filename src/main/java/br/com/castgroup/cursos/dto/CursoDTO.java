package br.com.castgroup.cursos.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.castgroup.cursos.entities.Curso;

public class CursoDTO {

	private Integer idCurso;
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private Integer qtdAlunos;
	private String categoria;

	public CursoDTO() {
		// TODO Auto-generated constructor stub
	}

	public CursoDTO(Integer idCurso, String descricao, LocalDate dataInicio, LocalDate dataTermino, Integer qtdAlunos,
			String categoria) {
		super();
		this.idCurso = idCurso;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.qtdAlunos = qtdAlunos;
		this.categoria = categoria;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	

	
	
	
	
	
	
}
