package com.project.daily_report.atividade.exception;

public class AtividadeNaoEncontradaException extends RuntimeException {

    public AtividadeNaoEncontradaException(Long id) {
        super("Atividade não encontrada com o id: " + id);
    }
}
