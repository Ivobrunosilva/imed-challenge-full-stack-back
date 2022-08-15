package com.imed.challenge.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imed.challenge.enums.OnlineStatus;

@Entity
@Table(name = "tb_paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 9136907584701107311L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_paciente")
	@SequenceGenerator(name = "sq_paciente", sequenceName = "sq_paciente", allocationSize = 1, initialValue = 6)
	private Long id;

	@Column(name = "date_created", nullable = false)
	private ZonedDateTime dateCreated;

//	@Column(nullable = false)
//	private String username;
//
//	@Column(nullable = false)
//	private String password;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;

	@Column(name = "total_appointment", nullable = false)
	private Integer totalAppointment;

	@Enumerated(EnumType.STRING)
	private OnlineStatus status;

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

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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
