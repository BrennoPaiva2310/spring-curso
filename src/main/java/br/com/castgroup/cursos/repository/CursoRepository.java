package br.com.castgroup.cursos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.castgroup.cursos.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {


	List<Curso> findByDataInicio(LocalDate dataInicio);


	@Query("select count(*) from Curso c where (:dataInicio <= c.dataInicio and :dataTermino >= c.dataInicio)" + "OR"
			+ "								   (:dataInicio <= c.dataTermino and :dataTermino >= c.dataTermino)" + "OR"
			+ "								   (:dataInicio >= c.dataInicio and :dataTermino <= c.dataTermino) "+"OR"
			+"  								(:dataInicio <= c.dataInicio and :dataTermino >= c.dataTermino)"	
			)
	Integer cont(LocalDate dataInicio, LocalDate dataTermino);
	
	@Query("select count(*) from Curso c where ((:dataInicio <= c.dataInicio and :dataTermino >= c.dataInicio)" + "OR"
			+ "								   (:dataInicio <= c.dataTermino and :dataTermino >= c.dataTermino)" + "OR"
			+ "								   (:dataInicio >= c.dataInicio and :dataTermino <= c.dataTermino) "+"OR"
			+"  								(:dataInicio <= c.dataInicio and :dataTermino >= c.dataTermino))" + "AND"
			+"									(c.idCurso != :idCurso)")
			
	Integer contPesquisa(LocalDate dataInicio, LocalDate dataTermino, Integer idCurso);
	
	Integer countByDescricao(String descricao);

	
	Integer countByDescricaoAndIdCursoNot(String descricao, Integer idCurso);
	
	



}