package com.imed.challenge.dto;

import javax.validation.constraints.NotNull;

import com.imed.challenge.enums.OnlineStatus;

public class AtualizaPacienteDTO {

	@NotNull
	private Integer totalAppointment;

	@NotNull
	private OnlineStatus status;

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
}
