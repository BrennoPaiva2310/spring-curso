package br.com.castgroup.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaRepository repository;

	//Metodo de Get
	@GetMapping
	public ResponseEntity<List<Categoria>> search() {

		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());

	}

}