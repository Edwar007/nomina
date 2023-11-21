package com.innovatech.solution.nomina.repository;


import com.innovatech.solution.nomina.dta.Pensiones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PensionRepository extends JpaRepository<Pensiones, Long> {
}
