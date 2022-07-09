package br.com.castgroup.cursos.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	CursoRepository repository;

	public void cadastrar(Curso curso) {
		validarData(curso);
		validarDataCadastro(curso.getDataInicio(), curso.getDataTermino());

		repository.save(curso);
	}

	public void validarDataCadastro(LocalDate dataInicio, LocalDate dataTermino) {

		Integer count = repository.countAllByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(dataInicio,
				dataTermino);
		if (count >= 1) {
			throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");

		}

	}

	public void validarData(Curso curso) {
		if (curso.getDataInicio().isBefore(LocalDate.now())) {

		}

	}

	public List<Curso> consultar() {
		return repository.findAll();

	}

	public Optional<Curso> consultarPorId(Integer id) {
		Optional<Curso> curso = repository.findById(id);

		if (curso.isEmpty()) {
			throw new RuntimeException("Curso Inexistente");

		}
		return curso;
	}

	public List<Curso> consultarPorDescricao(String descricao) {
		List<Curso> curso = repository.findByDescricao(descricao);

		if (curso.isEmpty()) {
			throw new RuntimeException("Descrição inexistente");

		}
		return curso;
	}

	// CONSULTA POR DATA
	public List<Curso> consultarPorData(LocalDate dataInicio, LocalDate dataTermino) {
		List<Curso> curso = repository.findByDataInicioBetween(dataInicio, dataTermino);

		if (curso.isEmpty()) {
			throw new RuntimeException("Periodo inexistente");

		}
		return curso;
	}

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
			throw new RuntimeException("Data invalida");
		}
	}

	public void verificarId(Integer id) {

		Optional<Curso> curso = repository.findById(id);
		if (curso.isEmpty()) {
			throw new RuntimeException("Curso Inexistente");

		}
	}

	@SuppressWarnings("deprecation")
	public void editar(Integer id, Curso curso) {
		Curso entity = repository.getOne(id);
		entity.setDescricao(curso.getDescricao());
		entity.setDataInicio(curso.getDataInicio());
		entity.setDataTermino(curso.getDataTermino());
		entity.setQtdAlunos(curso.getQtdAlunos());
		entity.setCategoria(curso.getCategoria());

		validarData(entity);
		repository.save(entity);
	}

}
