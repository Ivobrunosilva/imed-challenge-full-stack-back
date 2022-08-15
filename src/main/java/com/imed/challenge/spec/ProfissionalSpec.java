package com.imed.challenge.spec;

import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Profissional;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({ @Spec(path = "usuario.username", params = "username", spec = Like.class),
		@Spec(path = "name", spec = Like.class), @Spec(path = "status", spec = Equal.class),
		@Spec(path = "crm", spec = Equal.class) })
public interface ProfissionalSpec extends Specification<Profissional> {

}
