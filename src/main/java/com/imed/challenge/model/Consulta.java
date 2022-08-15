package com.imed.challenge.model;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.imed.challenge.enums.StatusConsulta;

@Entity
@Table(name = "tb_consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_consulta")
	@SequenceGenerator(name = "sq_consulta", sequenceName = "sq_consulta", allocationSize = 1, initialValue = 6)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "date_created", nullable = false)
	private ZonedDateTime dateCreated;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "profissional_id")
	private Profissional profissional;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "convenio_id")
	private Convenio convenio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "dt_consulta", nullable = false)
	//@JsonDeserialize(using = ZonedDateTimeDeserializer.class)
	private Date dtConsulta;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "dt_atualizacao")
	private ZonedDateTime dtAtualizacao;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusConsulta status;

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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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

	public Date getDtConsulta() {
		return dtConsulta;
	}

	public void setDtConsulta(Date dtConsulta) {
		this.dtConsulta = dtConsulta;
	}

	public ZonedDateTime getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(ZonedDateTime dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public StatusConsulta getStatus() {
		return status;
	}

	public void setStatus(StatusConsulta status) {
		this.status = status;
	}
}
