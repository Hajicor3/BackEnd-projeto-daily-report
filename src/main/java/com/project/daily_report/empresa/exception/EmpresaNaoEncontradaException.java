package com.project.daily_report.empresa.exception;

public class EmpresaNaoEncontradaException extends RuntimeException {

    public EmpresaNaoEncontradaException(Long id) {
        super("Empresa não encontrada com o id: " + id);
    }
}
