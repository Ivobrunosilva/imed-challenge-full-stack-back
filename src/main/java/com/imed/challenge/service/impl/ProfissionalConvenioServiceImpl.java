package com.imed.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imed.challenge.model.ProfissionalConvenio;
import com.imed.challenge.repository.ProfissionalConvenioRepository;
import com.imed.challenge.service.ProfissionalConvenioService;

@Service
@Transactional
public class ProfissionalConvenioServiceImpl implements ProfissionalConvenioService {

	private final ProfissionalConvenioRepository profissionalConvenioRepository;

	public ProfissionalConvenioServiceImpl(ProfissionalConvenioRepository profissionalConvenioRepository) {
		this.profissionalConvenioRepository = profissionalConvenioRepository;
	}

	@Override
	public ProfissionalConvenio vincular(ProfissionalConvenio profissionalConvenio) {
		return profissionalConvenioRepository.save(profissionalConvenio);
	}

	@Override
	public Optional<ProfissionalConvenio> findByProfissionalIdAndConvenioId(Long profissionalId, Long convenioId) {
		return profissionalConvenioRepository.findByProfissionalIdAndConvenioId(profissionalId, convenioId);
	}

	@Override
	public List<ProfissionalConvenio> listarConveniosPorProfissionalId(Long profissionalId) {
		return profissionalConvenioRepository.listarConveniosPorProfissionalId(profissionalId);
	}

}
