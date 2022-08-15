package com.imed.challenge.dto;

import javax.validation.constraints.NotNull;

public class ProfissionalConvenioDTO {

	@NotNull
	private Boolean flAtivo;

	public Boolean getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Boolean flAtivo) {
		this.flAtivo = flAtivo;
	}
}
