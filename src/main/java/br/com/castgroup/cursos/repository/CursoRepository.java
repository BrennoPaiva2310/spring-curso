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

//	//Integer countAllByDataInicioLessThanEqualAndDataTerminoGreaterThanEqualOrDataTerminoLessThanEqualAndDataInicioGreaterThanEqualOrDataTerminoLessThanEqualAndDataInicioGreaterThanEqual(LocalDate dataInicio,LocalDate dataTermino);
//	@Query("select c from Curso c where (:dataInicio <= c.dataInicio and :dataTermino >= c.dataInicio)"
//			+ "OR"
//			+ "								   (:dataInicio <= c.dataTermino and :dataTermino >= c.dataTermino)"
//			+ "OR"
//			+ "								   (:dataInicio >= c.dataInicio and :dataTermino <= c.dataTermino)")	
//	List<Curso>cursoRada(LocalDate dataInicio, LocalDate dataTermino);
//
//	@Query("select count(*) from Curso c where (:dataInicio <= c.dataInicio and :dataTermino >= c.dataInicio)"
//			+ "OR"
//			+ "								   (:dataInicio <= c.dataTermino and :dataTermino >= c.dataTermino)"
//			+ "OR"
//			+ "								   (:dataInicio >= c.dataInicio and :dataTermino <= c.dataTermino)")	
//	Integer cursoRada2(LocalDate dataInicio, LocalDate dataTermino);
//	
//	//List<Curso>findByDataInicioLessThanEqualAndDataTerminoGreaterThanEqualOrfindByDataTerminoLessThanEqualAndDataInicioGreaterThanEqualOr(LocalDate dataInicio,
//			//LocalDate dataTermino);
//
//	Integer countByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(LocalDate dataInicio,
//			LocalDate dataTermino);
//	
//	@Query(value = "from Curso t where dataInicio BETWEEN :dataInicio AND :dataTermino")
//	public List<Curso> getAllBetweenDates(@Param("dataInicio")LocalDate dataInicio,@Param("dataTermino")LocalDate dataTermino);
//	
//	List<Curso> findByDataInicioLessThanEqualAndDataTerminoGreaterThanEqual(LocalDate dataInicio,
//			LocalDate dataTermino);
//	
//}
	
}