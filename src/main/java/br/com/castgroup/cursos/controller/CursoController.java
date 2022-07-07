package br.com.castgroup.cursos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

	// Metodo de Post
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody CursoForm form) {
		try {
			Curso curso = new Curso();

			if (form.getDataInicio().isBefore(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data Menor que a atual");

			} else {
//				
				BeanUtils.copyProperties(form, curso);
				repository.save(curso);

			}

			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro" + e.getMessage());
		}

	}

	// Metodo de Get
	@GetMapping
	public ResponseEntity<List<CursoDTO>> search() {

		List<CursoDTO> list = new ArrayList<CursoDTO>();

		for (Curso curso : repository.findAll()) {
			CursoDTO item = new CursoDTO();
//
//			item.setIdCurso(curso.getIdCurso());
//			item.setDescricao(curso.getDescricao());
//			item.setDataInicio(curso.getDataInicio());
//			item.setDataTermino(curso.getDataTermino());
//			item.setQtdAlunos(curso.getQtdAlunos());
			
			item.setCategoria(curso.getCategoria().getCategoria());
			BeanUtils.copyProperties(curso, item);
			list.add(item);
		}

		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

	
	// Metodo de deletar
	@DeleteMapping(value = "delete/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable("idCurso") Integer idCurso) {
		try {
			Optional<Curso> item = repository.findById(idCurso);

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
			}

			Curso curso = item.get();

			if (curso.getDataTermino().isBefore(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data menor que a data atual");
			}
			
			repository.delete(curso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");

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
			CursoDTO item = new CursoDTO();

			Curso curso = list.get();

//			get.setIdCurso(curso.getIdCurso());
//			get.setDescricao(curso.getDescricao());
//			get.setDataInicio(curso.getDataInicio());
//			get.setDataTermino(curso.getDataTermino());
//			get.setQtdAlunos(curso.getQtdAlunos());
			item.setCategoria(curso.getCategoria().getCategoria());
			BeanUtils.copyProperties(curso, item);

			return ResponseEntity.status(HttpStatus.OK).body(item);

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
//				dto.setIdCurso(curso.getIdCurso());
//				dto.setDescricao(curso.getDescricao());
//				dto.setDataInicio(curso.getDataInicio());
//				dto.setDataTermino(curso.getDataTermino());
//				dto.setQtdAlunos(curso.getQtdAlunos());
//				dto.setCategoria(curso.getCategoria().getCategoria());
				dto.setCategoria(curso.getCategoria().getCategoria());
				BeanUtils.copyProperties(curso, dto);
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
			CursoDTO cursoDto = new CursoDTO();

			for (Curso curso : repository.findByDataInicioBetween(dataInicio, dataTermino)) {
//				cursoDto.setIdCurso(curso.getIdCurso());
//				cursoDto.setDescricao(curso.getDescricao());
//				cursoDto.setDataInicio(curso.getDataInicio());
//				cursoDto.setDataTermino(curso.getDataTermino());
//				cursoDto.setQtdAlunos(curso.getQtdAlunos());
//				cursoDto.setCategoria(curso.getCategoria().getCategoria());
				BeanUtils.copyProperties(curso, cursoDto);
				response.add(cursoDto);

			}

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}

	}

	// Metodo de Editar
	@PutMapping
	public ResponseEntity<String> update(@RequestBody AtualizacaoCursoForm form) {
		try {
			Optional<Curso> item = repository.findById(form.getIdCurso());

			if (item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
			} else {
				Curso curso = item.get();

//				curso.setDescricao(form.getDescricao());
//				curso.setDataInicio(form.getDataInicio());
//				curso.setDataTermino(form.getDataTermino());
//				curso.setQtdAlunos(form.getQtdAlunos());
//				curso.setCategoria(form.getCategoria());
				
				BeanUtils.copyProperties(form, curso);
				repository.save(curso);

				return ResponseEntity.status(HttpStatus.OK).body("Curso Editado");

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
		}

	}

}