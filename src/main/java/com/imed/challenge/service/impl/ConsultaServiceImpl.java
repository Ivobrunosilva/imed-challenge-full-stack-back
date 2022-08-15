package com.imed.challenge.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imed.challenge.exception.ProfessionalAlreadyInUseException;
import com.imed.challenge.model.Consulta;
import com.imed.challenge.repository.ConsultaRepository;
import com.imed.challenge.service.ConsultaService;

@Service
@Transactional
public class ConsultaServiceImpl implements ConsultaService {

	private final ConsultaRepository consultaMarcadaRepository;

	public ConsultaServiceImpl(ConsultaRepository consultaMarcadaRepository) {
		this.consultaMarcadaRepository = consultaMarcadaRepository;
	}

	@Override
	public Consulta adicionar(Consulta consultaMarcada) {
		return consultaMarcadaRepository.save(consultaMarcada);
	}

	@Override
	public Consulta atualizar(Consulta consultaMarcada) {
		return consultaMarcadaRepository.save(consultaMarcada);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Consulta> buscar(Long id) {
		return consultaMarcadaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Consulta> listar(Specification<Consulta> consultaMarcadaSpec, Pageable pageable) {
		return consultaMarcadaRepository.findAll(consultaMarcadaSpec, pageable);
	}

	@Override
	public void excluir(Consulta consultaMarcada) {
		consultaMarcadaRepository.delete(consultaMarcada);
	}

	@Override
	public void verificarProfissionalAgendado(Long profissionalId) {
		Optional<Consulta> consulta = consultaMarcadaRepository.verificarProfissionalAgendado(profissionalId);
		if (consulta.isPresent()) {
			throw new ProfessionalAlreadyInUseException("Esse profissional já está agendado.");
		}
	}

}
