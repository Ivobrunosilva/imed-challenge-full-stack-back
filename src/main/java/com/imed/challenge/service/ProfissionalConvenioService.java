package com.imed.challenge.service;

import java.util.List;
import java.util.Optional;

import com.imed.challenge.model.ProfissionalConvenio;

public interface ProfissionalConvenioService {

	ProfissionalConvenio vincular(ProfissionalConvenio profissionalConvenio);

	Optional<ProfissionalConvenio> findByProfissionalIdAndConvenioId(Long profissionalId, Long convenioId);

	List<ProfissionalConvenio> listarConveniosPorProfissionalId(Long profissionalId);

	// void desvincular(ProfissionalConvenio profissionalConvenio);
}
