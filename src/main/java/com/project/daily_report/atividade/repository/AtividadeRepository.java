package com.project.daily_report.atividade.repository;

import com.project.daily_report.atividade.domain.Atividade;
import com.project.daily_report.atividade.domain.CategoriaAtividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByData(LocalDate data);

    List<Atividade> findByEmpresaId(Long empresaId);

    List<Atividade> findByProjeto(String projeto);

    List<Atividade> findByCategoria(CategoriaAtividade categoria);

    List<Atividade> findByDataBetween(LocalDate dataInicial, LocalDate dataFinal);
}
