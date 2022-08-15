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

import com.imed.challenge.dto.AtualizaProfissionalDTO;
import com.imed.challenge.dto.ProfissionalDTO;
import com.imed.challenge.model.Profissional;
import com.imed.challenge.model.Usuario;
import com.imed.challenge.service.ProfissionalService;
import com.imed.challenge.service.UsuarioService;
import com.imed.challenge.spec.ProfissionalSpec;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/profissionais", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfissionalController {

	private final ProfissionalService profissionalService;
	
	private final UsuarioService usuarioService;
	
	private final PasswordEncoder passwordEncoder;

	public ProfissionalController(ProfissionalService profissionalService, UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
		this.profissionalService = profissionalService;
		this.usuarioService = usuarioService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public ResponseEntity<Page<Profissional>> listar(ProfissionalSpec profissionalSpec,
			@PageableDefault(page = 0, size = 10, sort = "dateCreated", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Profissional> profissionalPage = profissionalService.listar(profissionalSpec, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(profissionalPage);
	}

	@PostMapping
	public ResponseEntity<Object> adicionar(@RequestBody @Valid ProfissionalDTO profissionalDTO) {
		
		Optional<Usuario> usuarioOptional = usuarioService.buscarPorUsername(profissionalDTO.getUsuario().getUsername());
		
		if(usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse username já existe.");
		}
		
		Usuario usuario = new Usuario();
		//BeanUtils.copyProperties(profissionalDTO.getUsuario(), usuario);
		usuario.setUsername(profissionalDTO.getUsuario().getUsername());
		usuario.setPassword(passwordEncoder.encode(profissionalDTO.getUsuario().getPassword()));
		usuario.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));
		
		usuario = usuarioService.adicionar(usuario);
		
		Profissional profissional = new Profissional();
		BeanUtils.copyProperties(profissionalDTO, profissional);
		profissional.setUsuario(usuario);
		
		profissional.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));
		profissional = profissionalService.adicionar(profissional);
		return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id,
			@RequestBody @Valid AtualizaProfissionalDTO atualizaProfissionalDTO) {

		Optional<Profissional> profissionalOptional = profissionalService.buscar(id);

		if (!profissionalOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado.");
		}

		Profissional profissional = profissionalOptional.get();
		//BeanUtils.copyProperties(profissionalDTO, profissional);
		profissional.setName(atualizaProfissionalDTO.getName());
		profissional.setStatus(atualizaProfissionalDTO.getStatus());
		profissional.setCrm(atualizaProfissionalDTO.getCrm());
		profissional = profissionalService.atualizar(profissional);
		return ResponseEntity.status(HttpStatus.OK).body(profissional);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable(value = "id") Long id) {

		Optional<Profissional> profissionalOptional = profissionalService.buscar(id);

		if (!profissionalOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(profissionalOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "id") Long id) {

		Optional<Profissional> profissionalOptional = profissionalService.buscar(id);

		if (!profissionalOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional não encontrado.");
		}

		profissionalService.excluir(profissionalOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Profissional excluído com sucesso.");
	}

}
