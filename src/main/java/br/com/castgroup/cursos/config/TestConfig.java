package br.com.castgroup.cursos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.castgroup.cursos.repository.CursoRepository;

@Configuration
@Profile("teste")
public class TestConfig {
	
	private CursoRepository repository;

}
