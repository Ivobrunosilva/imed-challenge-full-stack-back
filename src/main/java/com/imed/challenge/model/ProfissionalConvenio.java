package com.imed.challenge.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_profissional_convenio")
public class ProfissionalConvenio {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_profissional_convenio")
	@SequenceGenerator(name = "sq_profissional_convenio", sequenceName = "sq_profissional_convenio", allocationSize = 1, initialValue = 6)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "date_created", nullable = false)
	private ZonedDateTime dateCreated;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "convenio_id")
	private Convenio convenio;
	
	@Column(name = "fl_ativo", nullable = false)
	private Boolean flAtivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Boolean getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Boolean flAtivo) {
		this.flAtivo = flAtivo;
	}
}
