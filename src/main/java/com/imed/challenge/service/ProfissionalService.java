package com.imed.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Profissional;

public interface ProfissionalService {

	Page<Profissional> listar(Specification<Profissional> profissionalSpec, Pageable pageable);

	Profissional adicionar(Profissional profissional);

	Profissional atualizar(Profissional profissional);

	Optional<Profissional> buscar(Long id);

	void excluir(Profissional profissional);

	List<Profissional> listarProfissionaisPorPacienteId(Long pacienteId);
}
