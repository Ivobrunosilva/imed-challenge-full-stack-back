package com.imed.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imed.challenge.model.ProfissionalConvenio;

@Repository
public interface ProfissionalConvenioRepository
		extends JpaRepository<ProfissionalConvenio, Long>, JpaSpecificationExecutor<ProfissionalConvenio> {

	@Query("select pc from ProfissionalConvenio pc where pc.profissional.id = :profissionalId and pc.convenio.id = :convenioId")
	Optional<ProfissionalConvenio> findByProfissionalIdAndConvenioId(@Param("profissionalId") Long profissionalId,
			@Param("convenioId") Long convenioId);

	@Query("select pc from ProfissionalConvenio pc where pc.profissional.id = :profissionalId")
	List<ProfissionalConvenio> listarConveniosPorProfissionalId(@Param("profissionalId") Long profissionalId);
}
