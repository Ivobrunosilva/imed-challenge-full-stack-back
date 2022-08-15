package com.imed.challenge.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Convenio;

public interface ConvenioService {

	Page<Convenio> listar(Specification<Convenio> convenioSpec, Pageable pageable);

	Convenio adicionar(Convenio convenio);

	Convenio atualizar(Convenio convenio);

	Optional<Convenio> buscar(Long id);

	void excluir(Convenio convenio);
}
