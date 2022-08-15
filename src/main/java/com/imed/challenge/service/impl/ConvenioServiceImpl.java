package com.imed.challenge.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imed.challenge.model.Convenio;
import com.imed.challenge.repository.ConvenioRepository;
import com.imed.challenge.service.ConvenioService;

@Service
@Transactional
public class ConvenioServiceImpl implements ConvenioService {

	private final ConvenioRepository convenioRepository;

	public ConvenioServiceImpl(ConvenioRepository convenioRepository) {
		this.convenioRepository = convenioRepository;
	}

	@Override
	public Convenio adicionar(Convenio convenio) {
		return convenioRepository.save(convenio);
	}

	@Override
	public Convenio atualizar(Convenio convenio) {
		return convenioRepository.save(convenio);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Convenio> buscar(Long id) {
		return convenioRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Convenio> listar(Specification<Convenio> convenioSpec, Pageable pageable) {
		return convenioRepository.findAll(convenioSpec, pageable);
	}

	@Override
	public void excluir(Convenio convenio) {
		convenioRepository.delete(convenio);
	}

}
