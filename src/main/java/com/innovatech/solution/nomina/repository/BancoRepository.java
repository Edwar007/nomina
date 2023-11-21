package com.innovatech.solution.nomina.repository;


import com.innovatech.solution.nomina.dta.Bancos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Bancos, Long> {
}
