package com.imed.challenge.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.imed.challenge.enums.OnlineStatus;

public class ProfissionalDTO {

	@NotBlank
	private String name;

	@NotNull
	private OnlineStatus status;

	@NotBlank
	private String crm;
	
	@NotNull
	@Valid
	private UsuarioDTO usuario;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
