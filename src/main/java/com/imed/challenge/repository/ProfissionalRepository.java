package com.imed.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imed.challenge.model.Profissional;

@Repository
public interface ProfissionalRepository
		extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional> {

	@Query("select c.profissional from Consulta c where c.paciente.id = :pacienteId")
	List<Profissional> listarProfissionaisPorPacienteId(@Param("pacienteId") Long pacienteId);

}
