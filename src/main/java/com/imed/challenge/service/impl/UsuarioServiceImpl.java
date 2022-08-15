package com.imed.challenge.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imed.challenge.model.Usuario;
import com.imed.challenge.repository.UsuarioRepository;
import com.imed.challenge.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario adicionar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario atualizar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> buscar(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> listar(Specification<Usuario> usuarioSpec, Pageable pageable) {
		return usuarioRepository.findAll(usuarioSpec, pageable);
	}

	@Override
	public void excluir(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarPorUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
