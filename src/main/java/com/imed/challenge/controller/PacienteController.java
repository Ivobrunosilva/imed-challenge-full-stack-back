package com.imed.challenge.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imed.challenge.dto.AtualizaPacienteDTO;
import com.imed.challenge.dto.PacienteDTO;
import com.imed.challenge.model.Paciente;
import com.imed.challenge.model.Usuario;
import com.imed.challenge.service.PacienteService;
import com.imed.challenge.service.UsuarioService;
import com.imed.challenge.spec.PacienteSpec;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {

	private final PacienteService pacienteService;

	private final PasswordEncoder passwordEncoder;
	
	private final UsuarioService usuarioService;

	public PacienteController(PacienteService pacienteService, PasswordEncoder passwordEncoder, UsuarioService usuarioService) {
		this.pacienteService = pacienteService;
		this.passwordEncoder = passwordEncoder;
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<Page<Paciente>> listar(PacienteSpec pacienteSpec,
			@PageableDefault(page = 0, size = 10, sort = "dateCreated", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Paciente> pacientePage = pacienteService.listar(pacienteSpec, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pacientePage);
	}

	@PostMapping
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> adicionar(@RequestBody @Valid PacienteDTO pacienteDTO) {
		Optional<Usuario> usuarioOptional = usuarioService
				.buscarPorUsername(pacienteDTO.getUsuario().getUsername());

		if (usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse username já existe.");
		}

		Usuario usuario = new Usuario();
		// BeanUtils.copyProperties(profissionalDTO.getUsuario(), usuario);
		usuario.setUsername(pacienteDTO.getUsuario().getUsername());
		usuario.setPassword(passwordEncoder.encode(pacienteDTO.getUsuario().getPassword()));
		usuario.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));

		usuario = usuarioService.adicionar(usuario);

		Paciente paciente = new Paciente();
		BeanUtils.copyProperties(pacienteDTO, paciente);
		paciente.setUsuario(usuario);
//		paciente.setPassword(passwordEncoder.encode(pacienteDTO.getPassword()));
		paciente.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));
		paciente = pacienteService.adicionar(paciente);
		return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id,
			@RequestBody @Valid AtualizaPacienteDTO atualizaPacienteDTO) {

		Optional<Paciente> pacienteOptional = pacienteService.buscar(id);

		if (!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
		}

		Paciente paciente = pacienteOptional.get();
		paciente.setStatus(atualizaPacienteDTO.getStatus());
		paciente.setTotalAppointment(atualizaPacienteDTO.getTotalAppointment());
		//BeanUtils.copyProperties(pacienteDTO, paciente);
//		paciente.setPassword(passwordEncoder.encode(pacienteDTO.getPassword()));
		paciente = pacienteService.atualizar(paciente);
		return ResponseEntity.status(HttpStatus.OK).body(paciente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable(value = "id") Long id) {

		Optional<Paciente> pacienteOptional = pacienteService.buscar(id);

		if (!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(pacienteOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "id") Long id) {

		Optional<Paciente> pacienteOptional = pacienteService.buscar(id);

		if (!pacienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado.");
		}

		pacienteService.excluir(pacienteOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Paciente excluído com sucesso.");
	}

}
