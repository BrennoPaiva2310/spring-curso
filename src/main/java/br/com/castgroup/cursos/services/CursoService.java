package br.com.castgroup.cursos.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.dto.CursoDTO;
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

		List<Curso> cursoTotal = repository.findAll();

		for (Curso c : cursoTotal) {

			if (dataInicio.equals(c.getDataInicio()) && dataTermino.equals(c.getDataTermino())) {

				throw new RuntimeException("Existe(m) curso(s) planejados(s) dentro do período informado.");

			}

		}
	}

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

	public List<CursoDTO> consultar() {

		List<CursoDTO> list = new ArrayList<CursoDTO>();
		List<LocalDate> dataInicio = new ArrayList<LocalDate>();

		for (Curso curso : repository.findAll()) {
			CursoDTO item = new CursoDTO();
//			

			item.setDataInicio(formatarDataInicio(curso.getDataInicio()));
			item.setDataTermino(formatarDataTermino(curso.getDataTermino()));

			item.setCategoria(curso.getCategoria().getCategoria());

			BeanUtils.copyProperties(curso, item);

			list.add(item);
			dataInicio.add(curso.getDataInicio());

		}

		return list;

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
			throw new RuntimeException("Deu ruim");
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
		verificarId(id);
		validarDataCadastro(curso.getDataInicio(), curso.getDataTermino());
		validarData(curso);

		// Botar a validação de data e a validacao de data cadastro
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
