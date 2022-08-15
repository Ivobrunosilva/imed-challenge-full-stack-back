package com.imed.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imed.challenge.model.Profissional;
import com.imed.challenge.repository.ProfissionalRepository;
import com.imed.challenge.service.ProfissionalService;

@Service
@Transactional
public class ProfissionalServiceImpl implements ProfissionalService {

	private final ProfissionalRepository profissionalRepository;

	public ProfissionalServiceImpl(ProfissionalRepository profissionalRepository) {
		this.profissionalRepository = profissionalRepository;
	}

	@Override
	public Profissional adicionar(Profissional profissional) {
		return profissionalRepository.save(profissional);
	}

	@Override
	public Profissional atualizar(Profissional profissional) {
		return profissionalRepository.save(profissional);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Profissional> buscar(Long id) {
		return profissionalRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Profissional> listar(Specification<Profissional> profissionalSpec, Pageable pageable) {
		return profissionalRepository.findAll(profissionalSpec, pageable);
	}

	@Override
	public void excluir(Profissional profissional) {
		profissionalRepository.delete(profissional);
	}

	@Override
	public List<Profissional> listarProfissionaisPorPacienteId(Long pacienteId) {
		return profissionalRepository.listarProfissionaisPorPacienteId(pacienteId);
	}

}
