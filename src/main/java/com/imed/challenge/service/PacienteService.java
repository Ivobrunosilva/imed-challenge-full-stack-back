package com.imed.challenge.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Paciente;

public interface PacienteService {

	Page<Paciente> listar(Specification<Paciente> pacienteSpec, Pageable pageable);

	Paciente adicionar(Paciente paciente);

	Paciente atualizar(Paciente paciente);

	Optional<Paciente> buscar(Long id);

	void excluir(Paciente paciente);

//	Optional<Paciente> buscarPorUsername(String username);
}
