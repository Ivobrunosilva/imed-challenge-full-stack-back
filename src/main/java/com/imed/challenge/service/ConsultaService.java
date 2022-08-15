package com.imed.challenge.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Consulta;

public interface ConsultaService {

	Page<Consulta> listar(Specification<Consulta> consultaSpec, Pageable pageable);

	Consulta adicionar(Consulta consulta);

	Consulta atualizar(Consulta consulta);

	Optional<Consulta> buscar(Long id);

	void excluir(Consulta consulta);

	void verificarProfissionalAgendado(Long profissionalId);
}
