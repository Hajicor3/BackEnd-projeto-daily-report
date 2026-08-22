package com.project.daily_report.empresa.dto;

import jakarta.validation.constraints.NotBlank;

public record EmpresaRequest(

        @NotBlank(message = "O nome da empresa é obrigatório")
        String nome,

        String descricao
) {
}
