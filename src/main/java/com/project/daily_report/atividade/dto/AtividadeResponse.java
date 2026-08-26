package com.project.daily_report.atividade.dto;

import com.project.daily_report.atividade.domain.CategoriaAtividade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AtividadeResponse(
        Long id,
        LocalDate data,
        String titulo,
        String descricao,
        String encarregado,
        Long empresaId,
        String empresaNome,
        String projeto,
        CategoriaAtividade categoria,
        LocalTime horaInicio,
        LocalTime horaFim,
        Integer minutosTrabalhados,
        String observacao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
