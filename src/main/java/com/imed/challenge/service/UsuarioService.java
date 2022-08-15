package com.imed.challenge.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Usuario;

public interface UsuarioService {

	Page<Usuario> listar(Specification<Usuario> usuarioSpec, Pageable pageable);

	Usuario adicionar(Usuario usuario);

	Usuario atualizar(Usuario usuario);

	Optional<Usuario> buscar(Long id);

	void excluir(Usuario usuario);

	Optional<Usuario> buscarPorUsername(String username);
}
