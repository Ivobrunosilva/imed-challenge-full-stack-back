package com.imed.challenge.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imed.challenge.model.Paciente;
import com.imed.challenge.repository.PacienteRepository;
import com.imed.challenge.service.PacienteService;

//@RequiredArgsConstructor
@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

	// private final PacienteRepository pacienteRepository;
	private final PacienteRepository pacienteRepository;

	public PacienteServiceImpl(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	@Override
	public Paciente adicionar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente atualizar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Paciente> buscar(Long id) {
		return pacienteRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Paciente> listar(Specification<Paciente> pacienteSpec, Pageable pageable) {
		return pacienteRepository.findAll(pacienteSpec, pageable);
	}

	@Override
	public void excluir(Paciente paciente) {
		pacienteRepository.delete(paciente);
	}

//	@Override
//	@Transactional(readOnly = true)
//	public Optional<Paciente> buscarPorUsername(String username) {
//		return pacienteRepository.findByUsername(username);
//	}

}
