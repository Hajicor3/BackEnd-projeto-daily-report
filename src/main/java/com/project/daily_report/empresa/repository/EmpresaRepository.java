package com.project.daily_report.empresa.repository;

import com.project.daily_report.empresa.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
