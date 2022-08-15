package com.imed.challenge.model;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imed.challenge.enums.OnlineStatus;

@Entity
@Table(name = "tb_profissional")
public class Profissional {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_profissional")
	@SequenceGenerator(name = "sq_profissional", sequenceName = "sq_profissional", allocationSize = 1, initialValue = 4)
	private Long id;

	@Column(name = "date_created", nullable = false)
	private ZonedDateTime dateCreated;

	@Column(nullable = false)
	private String name;
	
//	@Column(nullable = false)
//	private String username;
//
//	@Column(nullable = false)
//	private String password;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	private OnlineStatus status;

	private String crm;

	@OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<ProfissionalConvenio> profissionaisConvenios;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Set<ProfissionalConvenio> getProfissionaisConvenios() {
		return profissionaisConvenios;
	}

	public void setProfissionaisConvenios(Set<ProfissionalConvenio> profissionaisConvenios) {
		this.profissionaisConvenios = profissionaisConvenios;
	}

}
