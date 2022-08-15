package com.imed.challenge.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imed.challenge.model.Convenio;
import com.imed.challenge.model.Profissional;
import com.imed.challenge.model.ProfissionalConvenio;
import com.imed.challenge.service.ConvenioService;
import com.imed.challenge.service.ProfissionalConvenioService;
import com.imed.challenge.service.ProfissionalService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfissionalConvenioController {

	private final ProfissionalConvenioService profissionalConvenioService;

	private final ProfissionalService profissionalService;

	private final ConvenioService convenioService;

	public ProfissionalConvenioController(ProfissionalConvenioService profissionalConvenioService,
			ProfissionalService profissionalService, ConvenioService convenioService) {
		this.profissionalConvenioService = profissionalConvenioService;
		this.profissionalService = profissionalService;
		this.convenioService = convenioService;
	}

	@PostMapping("/profissionais/{profissionalId}/convenios/{convenioId}/vincula")
	public ResponseEntity<Object> vincular(@PathVariable(value = "profissionalId") Long profissionalId,
			@PathVariable(value = "convenioId") Long convenioId) {
		Optional<ProfissionalConvenio> profissionalConvenioOptional = profissionalConvenioService
				.findByProfissionalIdAndConvenioId(profissionalId, convenioId);
		if (profissionalConvenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Profissional já vinculado com esse convênio.");
		} else {
			Optional<Profissional> profissionalOptional = profissionalService.buscar(profissionalId);
			if (!profissionalOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado.");
			}
			Optional<Convenio> convenioOptional = convenioService.buscar(convenioId);
			if (!convenioOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convênio não encontrado.");
			}
			ProfissionalConvenio profissionalConvenio = new ProfissionalConvenio();
			profissionalConvenio.setFlAtivo(Boolean.TRUE);
			profissionalConvenio.setProfissional(profissionalOptional.get());
			profissionalConvenio.setConvenio(convenioOptional.get());
			profissionalConvenio.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));
			profissionalConvenio = profissionalConvenioService.vincular(profissionalConvenio);
			return ResponseEntity.status(HttpStatus.CREATED).body(profissionalConvenio);
		}
	}

	@PutMapping("/profissionais/{profissionalId}/convenios/{convenioId}/desvincula")
	public ResponseEntity<Object> desvincular(@PathVariable(value = "profissionalId") Long profissionalId,
			@PathVariable(value = "convenioId") Long convenioId) {

		Optional<ProfissionalConvenio> profissionalConvenioOptional = profissionalConvenioService
				.findByProfissionalIdAndConvenioId(profissionalId, convenioId);
		if (!profissionalConvenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não vinculado com esse convênio.");
		}
		ProfissionalConvenio profissionalConvenio = profissionalConvenioOptional.get();
		profissionalConvenio.setFlAtivo(Boolean.FALSE);
		profissionalConvenio = profissionalConvenioService.vincular(profissionalConvenio);
		return ResponseEntity.status(HttpStatus.CREATED).body(profissionalConvenio);
	}

	@GetMapping("/profissionais/{profissionalId}/convenios")
	public ResponseEntity<List<ProfissionalConvenio>> listarConveniosPorProfissionalId(
			@PathVariable(value = "profissionalId") Long profissionalId) {

		List<ProfissionalConvenio> profissionalConvenios = profissionalConvenioService
				.listarConveniosPorProfissionalId(profissionalId);

		return ResponseEntity.status(HttpStatus.OK).body(profissionalConvenios);
	}

}
