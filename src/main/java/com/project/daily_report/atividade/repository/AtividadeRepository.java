package com.project.daily_report.atividade.repository;

import com.project.daily_report.atividade.domain.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>,
        JpaSpecificationExecutor<Atividade> {
}
