package br.com.castgroup.cursos.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.exception.Exceptions;
import br.com.castgroup.cursos.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	CursoRepository repository;

	@PersistenceContext
	EntityManager em;

	@Autowired
	CriteriaService criteria;

	// SERVICO DE CADASTRAR
	public void cadastrar(Curso curso) {
		verificarCursoExiste(curso);
		validarData(curso);
		validarDataCadastro(curso.getDataInicio(), curso.getDataTermino());

		repository.save(curso);

	}
	
	//SERVICO DE CONSULTAR TUDO
	public List<Curso> consultar(String descricao, LocalDate dataInicio, LocalDate dataTermino) {

		return criteria.criteria(descricao, dataInicio, dataTermino);
	}
	
	
	//SERVICO DE CONSULTAR POR ID
	public Optional<Curso> consultarPorId(Integer id) {
		Optional<Curso> listCurso = repository.findById(id);

		if (listCurso.isEmpty()) {
			throw new Exceptions("Curso Inexistente");

		}

		return repository.findById(id);

	}

	
	//SERVICE DE DELETAR
	public void deleta(Integer id) {
		verificarId(id);
		verificarDataExclusao(repository.findById(id));

		repository.deleteById(id);
	}
	
	
	
	//SERVICE DE EDITAR
	public void editar(Curso curso) {

		Integer count = repository.contPesquisa(curso.getDataInicio(), curso.getDataTermino(), curso.getIdCurso());
		validarData(curso);
		verificarCursoExisteEditar(curso);
		if (count != 0) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");

		}

		repository.save(curso);

	}
	

	// VALIDAÇÃO DO PERIODO DE DATAS
	public void validarDataCadastro(LocalDate dataInicio, LocalDate dataTermino) {

		Integer count = repository.cont(dataInicio, dataTermino);

		System.out.println();
		if (count > 0) {

			throw new Exceptions("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
	}

	// VALIDACAO SE A DATA INICIO É ANTES DA DATA ATUAl
	public void validarData(Curso curso) {
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new Exceptions("Data antes da data atual.");

		}

	}

	




	// VERIFICAÇÕES PARA VER SE O CURSO TERMINOU
	public void verificarDataExclusao(Optional<Curso> curso) {

		Curso item = curso.get();

		if (item.getDataTermino().isBefore(LocalDate.now())) {
			throw new  Exceptions("Data antes da atual");
		}
	}

	
	//VERIFICAR SE O CURSO EXISTE
	public void verificarCursoExiste(Curso curso) {

		if (repository.countByDescricao(curso.getDescricao()) != 0) {
			throw new Exceptions("Curso Existente");
		}

	}

	//VERIFICAR SE O CURSO EXISTE DO EDITAR
	public void verificarCursoExisteEditar(Curso curso) {
		Integer i = repository.countByDescricaoAndIdCursoNot(curso.getDescricao(), curso.getIdCurso());

		if (i != 0) {
			throw new Exceptions("Curso Existente");
		}

	}

	//VERIFICAR O ID
	public void verificarId(Integer id) {

		Optional<Curso> curso = repository.findById(id);
		if (curso.isEmpty()) {
			throw new Exceptions("Curso Inexistente");

		}
	}

	
}
