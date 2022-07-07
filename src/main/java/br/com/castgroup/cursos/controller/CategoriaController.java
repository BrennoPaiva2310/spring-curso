package br.com.castgroup.cursos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.dto.CategoriaDTO;
import br.com.castgroup.cursos.entities.Categoria;
import br.com.castgroup.cursos.repository.CategoriaRepository;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	//@ApiOperation("Serviço para Buscar cursos")
		//@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
		
		//@CrossOrigin
		@GetMapping
		public ResponseEntity<List<CategoriaDTO>> search() {

			List<CategoriaDTO> list = new ArrayList<CategoriaDTO>();

			for (Categoria categoria : repository.findAll()) {
				CategoriaDTO item = new CategoriaDTO();
				
				item.setIdCategoria(categoria.getIdCategoria());
				item.setCategoria(categoria.getCategoria());
				

				list.add(item);
			}

			return ResponseEntity.status(HttpStatus.OK).body(list);

		}

}
