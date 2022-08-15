package com.imed.challenge.spec;

import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Consulta;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
	@Spec(path = "dtConsulta", spec = Equal.class, config = "dd/MM/yyyy HH:mm"),
	@Spec(path = "status", spec = Equal.class)
})
public interface ConsultaSpec extends Specification<Consulta> {

}
