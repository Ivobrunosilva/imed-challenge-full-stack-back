package com.imed.challenge.spec;

import org.springframework.data.jpa.domain.Specification;

import com.imed.challenge.model.Convenio;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({
	@Spec(path = "name", spec = Like.class),
	@Spec(path = "cnpj", spec = Equal.class),
	@Spec(path = "price", spec = Equal.class)
})
public interface ConvenioSpec extends Specification<Convenio> {

}
