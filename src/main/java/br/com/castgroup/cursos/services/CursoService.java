package br.com.castgroup.cursos.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	CursoRepository repository;

	@PersistenceContext
	EntityManager em;

	// SERVICO DE CADASTRAR
	public void cadastrar(Curso curso) {

		validarData(curso);
		validarDataCadastro(curso.getDataInicio(), curso.getDataTermino());
		verificarCursoExiste(curso);

		 repository.save(curso);

	}

	// SERVICO DE VALIDAR A DATA DO CADASTRO
	public void validarDataCadastro(LocalDate dataInicio, LocalDate dataTermino) {

		Integer count = repository.cont(dataInicio, dataTermino);

		System.out.println();
		if (count > 0) {

			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");
		}
	}

	// SERVICO PARA VALIDAR SE A DATA INICIO É ANTES DA DATA ATUAl
	public void validarData(Curso curso) {
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data antes da data atual.");

		}

	}

	public String formatarDataInicio(LocalDate dataInicio) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String dataInicioFormatada = dataInicio.format(formatter);

		return dataInicioFormatada;
	}

	public String formatarDataTermino(LocalDate dataTermino) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String dataTerminoFormatada = dataTermino.format(formatter);

		return dataTerminoFormatada;
	}

	public List<Curso> consultar(String descricao, LocalDate dataInicio, LocalDate dataTermino) {

		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = criteria.createQuery(Curso.class);

		Root<Curso> curso = criteriaQuery.from(Curso.class);
		List<Predicate> predList = new ArrayList<>();

		if (descricao != "" && descricao != null) {
			Predicate descricaoPredicate = criteria.equal(curso.get("descricao"), descricao);
			predList.add(descricaoPredicate);
		}

		if (dataInicio != null) {
			Predicate dataIniPredicate = criteria.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
			predList.add(dataIniPredicate);
		}

		if (dataTermino != null) {
			Predicate dataTerPredicate = criteria.lessThanOrEqualTo(curso.get("dataTermino"), dataTermino);
			predList.add(dataTerPredicate);
		}

		Predicate[] predicateArray = new Predicate[predList.size()];

		predList.toArray(predicateArray);

		criteriaQuery.where(predicateArray);

		TypedQuery<Curso> query = em.createQuery(criteriaQuery);

		return query.getResultList();
	}

	public Optional<Curso> consultarPorId(Integer id) {
		Optional<Curso> listCurso = repository.findById(id);

		if (listCurso.isEmpty()) {
			throw new RuntimeException("Curso Inexistente");

		}

		return repository.findById(id);

	}

	// CONSULTA POR DATA

	// DELETAR
	public void deleta(Integer id) {
		verificarId(id);
		verificarDataExclusao(repository.findById(id));

		repository.deleteById(id);
	}

	// VERIFICAÇÕES
	public void verificarDataExclusao(Optional<Curso> curso) {

		Curso item = curso.get();

		if (item.getDataTermino().isBefore(LocalDate.now())) {
			throw new RuntimeException("Curso terminado");
		}
	}

	public void verificarCursoExiste(Curso curso) {

			
			if (repository.countByDescricao(curso.getDescricao())!=0) {
				throw new RuntimeException("Curso Existente");
			}

	}

	public void verificarCursoExisteEditar(Curso curso) {
		Integer i = repository.countByDescricaoAndIdCursoNot(curso.getDescricao(), curso.getIdCurso());

		if (i != 0) {
			throw new RuntimeException("Curso Existente");
		}


	}

	public void verificarId(Integer id) {

		Optional<Curso> curso = repository.findById(id);
		if (curso.isEmpty()) {
			throw new RuntimeException("Curso Inexistente");

		}
	}

	public void editar(Curso curso) {

		Integer count = repository.contPesquisa(curso.getDataInicio(), curso.getDataTermino(), curso.getIdCurso());

		if (count != 0) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");

		}

		verificarCursoExisteEditar(curso);
		validarData(curso);

		repository.save(curso);

	}
}
