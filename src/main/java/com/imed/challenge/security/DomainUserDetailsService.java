package com.imed.challenge.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imed.challenge.model.Usuario;
import com.imed.challenge.service.UsuarioService;

@Service
public class DomainUserDetailsService implements UserDetailsService {

	private final UsuarioService usuarioService;

	public DomainUserDetailsService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioService.buscarPorUsername(username);
		if (!usuarioOptional.isPresent()) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_DEFALT"));
		return new User(usuarioOptional.get().getUsername(), usuarioOptional.get().getPassword(), authorities);
	}

}
