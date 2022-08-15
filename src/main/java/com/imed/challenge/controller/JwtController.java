package com.imed.challenge.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imed.challenge.dto.JwtDTO;
import com.imed.challenge.dto.UsuarioDTO;
import com.imed.challenge.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class JwtController {

	private final AuthenticationManager authenticationManager;

	public JwtController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/autentica")
	public ResponseEntity<JwtDTO> autenticar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuarioDTO.getUsername(), usuarioDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return ResponseEntity.status(HttpStatus.OK).body(new JwtDTO(JwtUtil.create(authentication)));
	}

}
