package br.com.castgroup.cursos.controller.form;

import java.time.LocalDate;

import br.com.castgroup.cursos.entities.Categoria;

//Quais campos que vão chegar do cliente
public class CursoForm {
	
	
	private String descricao;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private Integer qtdAlunos;
	private Categoria categoria;

	public CursoForm() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CursoForm(String descricao, LocalDate dataInicio, LocalDate dataTermino, Integer qtdAlunos,
			Categoria categoria) {
		super();
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.qtdAlunos = qtdAlunos;
		this.categoria = categoria;
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
