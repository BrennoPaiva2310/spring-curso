package br.com.castgroup.cursos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.castgroup.cursos.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	List<Curso> findByDescricao(String descricao);

	List<Curso> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFinal);

	List<Curso> findByDataInicio(LocalDate dataInicio);

	List<Curso> findByDataTermino(LocalDate dataTermino);

	Integer countAllByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(LocalDate dataInicio,
			LocalDate dataTermino);

}
