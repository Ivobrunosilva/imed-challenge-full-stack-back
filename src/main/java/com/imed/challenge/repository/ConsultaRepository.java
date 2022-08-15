package com.imed.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imed.challenge.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>, JpaSpecificationExecutor<Consulta> {

	@Query("select c from Consulta c where c.profissional.id = :profissionalId and c.status = 'MARCADA'")
	Optional<Consulta> verificarProfissionalAgendado(@Param("profissionalId") Long profissionalId);

}
