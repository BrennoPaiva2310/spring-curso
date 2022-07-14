package br.com.castgroup.cursos.services;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
@Service
public class AuditingService implements AuditorAware<String> {


	public Optional<String>getCurrentAuditor(){
		return Optional.of("Brenno de Paiva");
	}

}
