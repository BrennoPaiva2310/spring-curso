package br.com.castgroup.cursos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.castgroup.cursos.repository.CategoriaRepository;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	//@ApiOperation("Serviço para Buscar cursos")
		//@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
		
		//@CrossOrigin
//		@GetMapping
//		public ResponseEntity<List<CategoriaDTO>> search() {
//
//			List<CategoriaDTO> list = new ArrayList<CategoriaDTO>();
//
//			for (Categoria categoria : repository.findAll()) {
//				CategoriaDTO item = new CategoriaDTO();
//				
//				item.setIdCategoria(categoria.getIdCategoria());
//				item.setCategoria(categoria.getCategoria());
//				
//
//				list.add(item);
//			}
//
//			return ResponseEntity.status(HttpStatus.OK).body(list);
//
//		}

}
