package br.com.castgroup.cursos.controller;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	// https://medium.com/@rodrigoventuri123/auditoria-com-spring-data-jpa-fbb54c4b443e
	// https://stackoverflow.com/questions/39784344/check-date-between-two-other-dates-spring-data-jpa
	// https://consolelog.com.br/formatar-data-datepipe-angular/
	// https://www.baeldung.com/spring-data-criteria-queries
	// https://www.guj.com.br/t/como-fazer-refresh-na-tela-usando-angular/374418/2
	// https://stackoverflow.com/questions/51200708/not-equals-condition-when-using-spring-crudrepository

	@Autowired
	CursoService service;

	// Metodo de Post
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Curso curso) {

		service.cadastrar(curso);
		return ResponseEntity.ok().body("Curso cadastrado com sucesso");

	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Curso>> buscar(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataTermino) {
		List<Curso> curso = service.consultar(descricao, dataInicio, dataTermino);

		return ResponseEntity.ok().body(curso);
	}

	// Metodo de get por id
	@CrossOrigin
	@GetMapping("/{idCurso}")
	public ResponseEntity<?> listarId(@PathVariable("idCurso") Integer idCurso) {

		return ResponseEntity.ok().body(service.consultarPorId(idCurso));

	}

	// Metodo de deletar
	@CrossOrigin
	@DeleteMapping(value = "/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable("idCurso") Integer idCurso) {

		service.deleta(idCurso);
		return ResponseEntity.ok().body("Curso excluido com ducesso");

	}

	// Metodo de Editar
	@CrossOrigin
	@PutMapping(value = "/editar")
	public ResponseEntity<String> update(@RequestBody Curso curso) {

		service.editar(curso);
		return ResponseEntity.status(HttpStatus.OK).body("Curso Editado");

	}

}