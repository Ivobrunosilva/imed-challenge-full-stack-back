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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imed.challenge.dto.ConvenioDTO;
import com.imed.challenge.model.Convenio;
import com.imed.challenge.service.ConvenioService;
import com.imed.challenge.spec.ConvenioSpec;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/convenios", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConvenioController {

	private final ConvenioService convenioService;

	public ConvenioController(ConvenioService convenioService) {
		this.convenioService = convenioService;
	}

	@GetMapping
	public ResponseEntity<Page<Convenio>> listar(ConvenioSpec convenioSpec,
			@PageableDefault(page = 0, size = 10, sort = "dateCreated", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Convenio> convenioPage = convenioService.listar(convenioSpec, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(convenioPage);
	}

	@PostMapping
	public ResponseEntity<Convenio> adicionar(@RequestBody @Valid ConvenioDTO convenioDTO) {
		Convenio convenio = new Convenio();
		BeanUtils.copyProperties(convenioDTO, convenio);
		convenio.setDateCreated(ZonedDateTime.now(ZoneId.of("UTC")));
		convenio = convenioService.adicionar(convenio);
		return ResponseEntity.status(HttpStatus.CREATED).body(convenio);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id,
			@RequestBody @Valid ConvenioDTO convenioDTO) {

		Optional<Convenio> convenioOptional = convenioService.buscar(id);

		if (!convenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convênio não encontrado.");
		}

		Convenio convenio = convenioOptional.get();
		BeanUtils.copyProperties(convenioDTO, convenio);
		convenio = convenioService.adicionar(convenio);
		return ResponseEntity.status(HttpStatus.OK).body(convenio);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable(value = "id") Long id) {

		Optional<Convenio> convenioOptional = convenioService.buscar(id);

		if (!convenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convênio não encontrado.");
		}

		return ResponseEntity.status(HttpStatus.OK).body(convenioOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "id") Long id) {

		Optional<Convenio> convenioOptional = convenioService.buscar(id);

		if (!convenioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Convênio não encontrado.");
		}

		convenioService.excluir(convenioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Convênio excluído com sucesso.");
	}

}
