package br.com.castgroup.cursos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.castgroup.cursos.services.AuditingService;

@EnableWebMvc
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingService")
public class CursosApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
		
	}
	@Bean
	AuditorAware<String> auditorProvider(){
		return new AuditingService();
		
	}
		
	
	
}

