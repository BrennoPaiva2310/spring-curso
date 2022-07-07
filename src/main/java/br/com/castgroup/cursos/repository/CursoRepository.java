package br.com.castgroup.cursos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.castgroup.cursos.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {


	List<Curso> findByDescricao(String descricao);
	


//	List<Curso> findByPeriodo(LocalDate dataInicio, LocalDate dataTermino);

 List<Curso> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFinal);


}
