package com.imed.challenge.dto;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imed.challenge.enums.StatusConsulta;

public class ConsultaDTO {

	@NotNull
	private Long pacienteId;

	@NotNull
	private Long profissionalId;

	@NotNull
	private Long convenioId;

	@NotNull
	//private ZonedDateTime dtConsulta;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dtConsulta;

	@NotNull
	private StatusConsulta status;

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getProfissionalId() {
		return profissionalId;
	}

	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}

	public Long getConvenioId() {
		return convenioId;
	}

	public void setConvenioId(Long convenioId) {
		this.convenioId = convenioId;
	}

	public Date getDtConsulta() {
		return dtConsulta;
	}

	public void setDtConsulta(Date dtConsulta) {
		this.dtConsulta = dtConsulta;
	}

	public StatusConsulta getStatus() {
		return status;
	}

	public void setStatus(StatusConsulta status) {
		this.status = status;
	}
}
