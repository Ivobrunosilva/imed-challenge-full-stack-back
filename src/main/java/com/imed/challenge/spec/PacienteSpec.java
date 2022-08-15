package com.imed.challenge.spec;

import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Paciente;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({ @Spec(path = "usuario.username", params = "username", spec = Like.class),
		@Spec(path = "status", spec = Equal.class) })
public interface PacienteSpec extends Specification<Paciente> {

}
