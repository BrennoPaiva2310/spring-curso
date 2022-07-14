package br.com.castgroup.cursos.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class  AbstractAuditingEntity {
	
	@CreatedBy
	@Column(name="criado_Por", nullable=false, length=50, updatable =  false)
	private String criadoPor;
	
	@CreatedDate
	@Column(name="data_criacao", updatable =  false)
	private Instant dataCriacao;
	
	@LastModifiedBy
	@Column(name="ultima_modificacao_por", length=50)
	private String ultimaModificacaoPor;
	
	@LastModifiedDate
	@Column(name="ultima_data_modificacao")
	private String ultima_data_modificacao;
}
