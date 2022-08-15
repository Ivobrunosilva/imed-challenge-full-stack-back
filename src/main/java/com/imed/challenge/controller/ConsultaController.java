package com.imed.challenge.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imed.challenge.dto.ConsultaDTO;
import com.imed.challenge.enums.StatusConsulta;
import com.imed.challenge.exception.ProfessionalAlreadyInUseException;
import com.imed.challenge.model.Consulta;
import com.imed.challenge.model.Convenio;
import com.imed.challenge.model.Paciente;
import com.imed.challenge.model.Profissional;
import com.imed.challenge.model.ProfissionalConvenio;
import com.imed.challenge.service.ConsultaService;
import com.imed.challenge.service.ConvenioService;
import com.imed.challenge.service.PacienteService;
import com.imed.challenge.service.ProfissionalConvenioService;
import com.imed.challenge.service.ProfissionalService;
import com.imed.challenge.spec.ConsultaSpec;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/consultas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultaController {

	private final ConsultaService consultaService;

	private final PacienteService pacienteService;

	private final ProfissionalService profissionalService;

	private final ConvenioService convenioService;

	private final ProfissionalConvenioService profissionalConvenioService;

	public ConsultaController(ConsultaService consultaService, PacienteService pacienteService,
			ProfissionalService profissionalService, ConvenioService convenioService,
			ProfissionalConvenioService profissionalConvenioService) {
		this.consultaService = consultaService;
		this.pacienteService = pacienteService;
		this.profissionalService = profissionalService;
		this.convenioService = convenioService;
		this.profissionalConvenioService = profissionalConvenioService;
	}

	@GetMapping
	public ResponseEntity<Page<Consulta>> listar(ConsultaSpec consultaSpec,
			@PageableDefault(page = 0, size = 10, sort = "dateCreated", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Consulta> consultaPage = consultaService.listar(consultaSpec, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(consultaPage);
	}

	@PostMapping
	public ResponseEntity<Object> adicionar(@RequestBody @Valid ConsultaDTO consultaDTO) {
		Optional<Paciente> pacienteOptional = pacienteService.buscar(consultaDTO.getPacienteId());
		if (!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
		}
		Optional<Profissional> profissionalOptional = profissionalService.buscar(consultaDTO.getProfissionalId());
		if (!profissionalOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado.");
		}
		Optional<Convenio> convenioOptional = convenioService.buscar(consultaDTO.getConvenioId());
		if (!convenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convênio não encontrado.");
		}

		Optional<ProfissionalConvenio> profissionalConvenioOptional = profissionalConvenioService
				.findByProfissionalIdAndConvenioId(consultaDTO.getProfissionalId(), consultaDTO.getConvenioId());
		if (!profissionalConvenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não vinculado com esse convênio.");
		}

		consultaService.verificarProfissionalAgendado(consultaDTO.getProfissionalId());

		Consulta consulta = new Consulta();
		consulta.setDtConsulta(consultaDTO.getDtConsulta());
		consulta.setStatus(consultaDTO.getStatus());
		consulta.setPaciente(pacienteOptional.get());
		consulta.setProfissional(profissionalOptional.get());
		consulta.setConvenio(convenioOptional.get());
		consulta.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));
		consulta = consultaService.adicionar(consulta);
		return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id,
			@RequestBody @Valid ConsultaDTO consultaDTO) {

		Optional<Consulta> consultaOptional = consultaService.buscar(id);

		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
		}

		Optional<Paciente> pacienteOptional = pacienteService.buscar(consultaDTO.getPacienteId());
		if (!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
		}
		Optional<Profissional> profissionalOptional = profissionalService.buscar(consultaDTO.getProfissionalId());
		if (!profissionalOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado.");
		}
		Optional<Convenio> convenioOptional = convenioService.buscar(consultaDTO.getConvenioId());
		if (!convenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convênio não encontrado.");
		}
		
		Optional<ProfissionalConvenio> profissionalConvenioOptional = profissionalConvenioService
				.findByProfissionalIdAndConvenioId(consultaDTO.getProfissionalId(), consultaDTO.getConvenioId());
		if (!profissionalConvenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não vinculado com esse convênio.");
		}
		
		consultaService.verificarProfissionalAgendado(consultaDTO.getProfissionalId());

		Consulta consulta = consultaOptional.get();
		consulta.setDtConsulta(consultaDTO.getDtConsulta());
		consulta.setStatus(consultaDTO.getStatus());
		consulta.setPaciente(pacienteOptional.get());
		consulta.setProfissional(profissionalOptional.get());
		consulta.setConvenio(convenioOptional.get());
		consulta.setDtAtualizacao(ZonedDateTime.now(ZoneId.of("UTC")));
		consulta = consultaService.atualizar(consulta);
		return ResponseEntity.status(HttpStatus.OK).body(consulta);
	}

	@PutMapping("/{id}/desmarca")
	public ResponseEntity<Object> desmarcar(@PathVariable(value = "id") Long id) {

		Optional<Consulta> consultaOptional = consultaService.buscar(id);

		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
		}

		Consulta consulta = consultaOptional.get();
		consulta.setStatus(StatusConsulta.CANCELADA);
		consulta.setDtAtualizacao(ZonedDateTime.now(ZoneId.of("UTC")));
		consulta = consultaService.atualizar(consulta);
		return ResponseEntity.status(HttpStatus.OK).body("Consulta desmarcada com sucesso.");
	}

	@PutMapping("/{id}/efetiva")
	public ResponseEntity<Object> efetivar(@PathVariable(value = "id") Long id) {

		Optional<Consulta> consultaOptional = consultaService.buscar(id);

		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
		}

		Consulta consulta = consultaOptional.get();
		consulta.setStatus(StatusConsulta.EFETIVADA);
		consulta.setDtAtualizacao(ZonedDateTime.now(ZoneId.of("UTC")));
		consulta = consultaService.atualizar(consulta);
		return ResponseEntity.status(HttpStatus.OK).body("Consulta efetivada com sucesso.");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable(value = "id") Long id) {

		Optional<Consulta> consultaOptional = consultaService.buscar(id);

		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(consultaOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "id") Long id) {

		Optional<Consulta> consultaOptional = consultaService.buscar(id);

		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada.");
		}

		consultaService.excluir(consultaOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Consulta excluída com sucesso.");
	}

	@ExceptionHandler(ProfessionalAlreadyInUseException.class)
	public ResponseEntity<Object> handleProfessionalAlreadyInUse(ProfessionalAlreadyInUseException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}

}
