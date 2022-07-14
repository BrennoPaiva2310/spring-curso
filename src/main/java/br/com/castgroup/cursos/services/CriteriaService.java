package br.com.castgroup.cursos.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Service;

import br.com.castgroup.cursos.entities.Curso;
@Service
public  class CriteriaService {
	

	@PersistenceContext
	 private  EntityManager em;
	
	public  List<Curso> criteria(String descricao, LocalDate dataInicio, LocalDate dataTermino) {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<Curso> criteriaQuery = criteria.createQuery(Curso.class);

		Root<Curso> curso = criteriaQuery.from(Curso.class);
		List<Predicate> predList = new ArrayList<>();

		if (descricao != "" && descricao != null) {
			Predicate descricaoPredicate = criteria.equal(curso.get("descricao"), descricao);
			predList.add(descricaoPredicate);
		}

		if (dataInicio != null) {
			Predicate dataInicioPredicate = criteria.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
			predList.add(dataInicioPredicate);
		}

		if (dataTermino != null) {
			Predicate dataTerminoPredicate = criteria.lessThanOrEqualTo(curso.get("dataTermino"), dataTermino);
			predList.add(dataTerminoPredicate);
		}

		Predicate[] predicateArray = new Predicate[predList.size()];

		predList.toArray(predicateArray);

		criteriaQuery.where(predicateArray);

		TypedQuery<Curso> query = em.createQuery(criteriaQuery);


        return query.getResultList();
	}

}
