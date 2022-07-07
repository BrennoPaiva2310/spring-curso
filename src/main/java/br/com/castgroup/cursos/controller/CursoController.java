package br.com.castgroup.cursos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.controller.form.AtualizacaoCursoForm;
import br.com.castgroup.cursos.controller.form.CursoForm;
import br.com.castgroup.cursos.dto.CursoDTO;
import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.repository.CursoRepository;

@RestController
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	CursoRepository repository;


	// @ApiOperation("Serviço para Cadastrar")
	// @CrossOrigin
	// Metodo de Post
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody CursoForm form) {
		try {
			Curso curso = new Curso();

			if (form.getDataInicio().isBefore(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.OK).body("Data Menor que data Atual");

			} else {
				curso.setDescricao(form.getDescricao());
				curso.setDataInicio(form.getDataInicio());
				curso.setDataTermino(form.getDataTermino());
				curso.setQtdAlunos(form.getQtdAlunos());
				curso.setCategoria(form.getCategoria());
				repository.save(curso);

			}

			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro" + e.getMessage());
		}

	}
	
	
	// @ApiOperation("Serviço para Buscar cursos")
	// @CrossOrigin
	// Metodo de Get
	@GetMapping
	public ResponseEntity<List<CursoDTO>> search() {

		List<CursoDTO> list = new ArrayList<CursoDTO>();

		for (Curso curso : repository.findAll()) {
			CursoDTO item = new CursoDTO();

			item.setIdCurso(curso.getIdCurso());
			item.setDescricao(curso.getDescricao());
			item.setDataInicio(curso.getDataInicio());
			item.setDataTermino(curso.getDataTermino());
			item.setQtdAlunos(curso.getQtdAlunos());
			item.setCategoria(curso.getCategoria().getCategoria());

			list.add(item);
		}

		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

	// @ApiOperation("Serviço para Deletar")
	// Metodo de deletar
	// @CrossOrigin
	@DeleteMapping(value = "delete/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable("idCurso") Integer idCurso) {
		try {
			Optional<Curso> item = repository.findById(idCurso);

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {
				Curso curso = item.get();
				repository.delete(curso);
				return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}

	
	// Metodo de get por id
	@GetMapping("/{idCurso}")
	public ResponseEntity<CursoDTO> listarId(@PathVariable("idCurso") Integer idCurso) {

		Optional<Curso> list = repository.findById(idCurso);

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			CursoDTO get = new CursoDTO();

			Curso curso = list.get();

			get.setIdCurso(curso.getIdCurso());
			get.setDescricao(curso.getDescricao());
			get.setDataInicio(curso.getDataInicio());
			get.setDataTermino(curso.getDataTermino());
			get.setQtdAlunos(curso.getQtdAlunos());
			get.setCategoria(curso.getCategoria().getCategoria());

			return ResponseEntity.status(HttpStatus.OK).body(get);

		}

	}

	// Metodo de busca por descrição
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<?> listarId(@PathVariable("descricao") String descricao) {

		List<Curso> list = repository.findByDescricao(descricao);
		List<CursoDTO> response = new ArrayList<>();

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não achado");

		} else {
			CursoDTO dto = new CursoDTO();

			for (Curso curso : repository.findByDescricao(descricao)) {
				dto.setIdCurso(curso.getIdCurso());
				dto.setDescricao(curso.getDescricao());
				dto.setDataInicio(curso.getDataInicio());
				dto.setDataTermino(curso.getDataTermino());
				dto.setQtdAlunos(curso.getQtdAlunos());
				dto.setCategoria(curso.getCategoria().getCategoria());
				response.add(dto);

			}

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}

	}

	
	// Metodo de busca pelo intervalo de datas
	@GetMapping(value = "/periodo/{dataInicio}/{dataTermino}")
	public ResponseEntity<?> listarData(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataTermino) {

		List<Curso> list = repository.findByDataInicioBetween(dataInicio, dataTermino);
		List<CursoDTO> response = new ArrayList<>();

		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não achado");

		} else {
			CursoDTO dto = new CursoDTO();

			for (Curso curso : repository.findByDataInicioBetween(dataInicio, dataTermino)) {
				dto.setIdCurso(curso.getIdCurso());
				dto.setDescricao(curso.getDescricao());
				dto.setDataInicio(curso.getDataInicio());
				dto.setDataTermino(curso.getDataTermino());
				dto.setQtdAlunos(curso.getQtdAlunos());
				dto.setCategoria(curso.getCategoria().getCategoria());
				response.add(dto);

			}

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}

	}

	
	// @ApiOperation("Serviço para Atualizar")
	// @CrossOrigin
	// Metodo de Editar
	@PutMapping
	public ResponseEntity<String> update(@RequestBody AtualizacaoCursoForm request) {
		try {
			Optional<Curso> item = repository.findById(request.getIdCurso());

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
			} else {
				Curso curso = item.get();

				curso.setDescricao(request.getDescricao());
				curso.setDataInicio(request.getDataInicio());
				curso.setDataTermino(request.getDataTermino());
				curso.setQtdAlunos(request.getQtdAlunos());
				curso.setCategoria(request.getCategoria());

				repository.save(curso);

				return ResponseEntity.status(HttpStatus.OK).body("Curso Editado");

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}

}