package com.project.daily_report.atividade.dto;

import com.project.daily_report.atividade.domain.CategoriaAtividade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtividadeRequest(

        @NotNull(message = "A data é obrigatória")
        LocalDate data,

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        String descricao,

        @NotBlank
        String encarregado,

        @NotNull(message = "A empresa é obrigatória")
        Long empresaId,

        String projeto,

        @NotNull(message = "A categoria é obrigatória")
        CategoriaAtividade categoria,

        @NotNull(message = "A hora inicial é obrigatória")
        LocalTime horaInicio,

        @NotNull(message = "A hora final é obrigatória")
        LocalTime horaFim,

        String observacao
) {
}
