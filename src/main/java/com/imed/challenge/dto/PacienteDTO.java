package com.imed.challenge.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.imed.challenge.enums.OnlineStatus;

public class PacienteDTO {

	@NotNull
	private Integer totalAppointment;

	@NotNull
	private OnlineStatus status;

	@NotNull
	@Valid
	private UsuarioDTO usuario;

	public Integer getTotalAppointment() {
		return totalAppointment;
	}

	public void setTotalAppointment(Integer totalAppointment) {
		this.totalAppointment = totalAppointment;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
