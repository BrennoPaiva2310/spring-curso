package br.com.castgroup.cursos.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.CursosApplication;
import br.com.castgroup.cursos.dto.CursoDTO;
import br.com.castgroup.cursos.entities.Curso;
import br.com.castgroup.cursos.services.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController {

	// Fontes de pesquisa
	//
	// Aula da Professora
	// https://www.baeldung.com/apache-commons-beanutils#:~:text=BeanUtils%20class%20provides%20a%20copyProperties,is%20same%20in%20both%20objects.
	// https://cursos.alura.com.br
	// https://udemy.com.br
	// https://cursos.alura.com.br/forum/topico-endpoint-busca-por-intervalo-de-datas-142031
	// https://gasil96.medium.com/entenda-spring-data-jpa-e-conhe%C3%A7a-o-query-by-example-864bcce208f6
	// https://stackoverflow.com/questions/39784344/check-date-between-two-other-dates-spring-data-jpa
	// https://www.baeldung.com/spring-data-derived-queries
	// https://thorben-janssen.com/ultimate-guide-derived-queries-with-spring-data-jpa/

	@Autowired
	CursoService service;

	// Metodo de Post
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Curso curso) {

		try {
			service.cadastrar(curso);
			return ResponseEntity.ok().body("Curso cadastrado com sucesso");

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());

		}

	}

	// Metodo de Get
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<CursoDTO>> buscar() {

		List<CursoDTO> curso = service.consultar();

		return ResponseEntity.ok().body(curso);
	}

	// Metodo de get por id
	@CrossOrigin
	@GetMapping("/{idCurso}")
	public ResponseEntity<?> listarId(@PathVariable("idCurso") Integer idCurso) {

		try {
			return ResponseEntity.ok().body(service.consultarPorId(idCurso));

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body("Erro :" + e.getMessage());
		}

	}

	// Metodo de deletar
	@CrossOrigin
	@DeleteMapping(value = "/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable("idCurso") Integer idCurso) {
		try {
			service.deleta(idCurso);
			return ResponseEntity.ok().body("Curso excluido com ducesso");

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro : " + e.getMessage());
		}

	}

	// Metodo de pesquisa por descrição
	@CrossOrigin
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<Object> buscarPorDescricao(@PathVariable("descricao") String descricao) {

		try {

			return ResponseEntity.ok().body(service.consultarPorDescricao(descricao));

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro :" + e.getMessage());
		}

	}

	// Metodo de pesquisa por intervalo de datas
	@CrossOrigin
	@GetMapping(value = "/periodo/{dataInicio}/{dataTermino}")
	public ResponseEntity<?> listarData(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataTermino) {

		try {

			return ResponseEntity.ok().body(service.consultarPorData(dataInicio, dataTermino));

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro :" + e.getMessage());
		}

	}

	// Metodo de Editar
	@CrossOrigin
	@PutMapping(value = "/editar")
	public ResponseEntity<String> update(@RequestBody Curso curso) {
		try {
			Integer item = curso.getIdCurso();
			service.editar(item, curso);

			return ResponseEntity.status(HttpStatus.OK).body("Curso Editado");

		} catch (

		Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());

		}
	}

}