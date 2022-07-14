package br.com.castgroup.cursos.form;

public class CursoForm {

	private Integer idCurso;
	private String descricao;
	private String dataInicio;
	private String dataTermino;
	private String qtdAlunos;

	private Integer categoria;
	
	public CursoForm() {
		// TODO Auto-generated constructor stub
	}

	public CursoForm(Integer idCurso, String descricao, String dataInicio, String dataTermino, String qtdAlunos,
			Integer categoria) {
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

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getQtdAlunos() {
		return qtdAlunos;
	}

	public void setQtdAlunos(String qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	
	
	
	
	
	
	
	

	
	

	
}
