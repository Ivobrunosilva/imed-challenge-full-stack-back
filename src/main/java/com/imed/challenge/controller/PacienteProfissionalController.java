package com.imed.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imed.challenge.model.Profissional;
import com.imed.challenge.service.ProfissionalService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteProfissionalController {

	private final ProfissionalService profissionalService;

	public PacienteProfissionalController(ProfissionalService profissionalService) {
		this.profissionalService = profissionalService;
	}

	@GetMapping("/pacientes/{pacienteId}/profissionais")
	public ResponseEntity<List<Profissional>> listarProfissionaisPorPacienteId(
			@PathVariable(value = "pacienteId") Long pacienteId) {
		List<Profissional> profissionais = profissionalService.listarProfissionaisPorPacienteId(pacienteId);
		return ResponseEntity.status(HttpStatus.OK).body(profissionais);
	}

}
