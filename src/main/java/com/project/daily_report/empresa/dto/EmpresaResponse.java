package com.project.daily_report.empresa.dto;

import java.time.LocalDateTime;

public record EmpresaResponse(
        Long id,
        String nome,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
