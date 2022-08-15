package com.imed.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.imed.challenge.model.Convenio;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long>, JpaSpecificationExecutor<Convenio> {

}
