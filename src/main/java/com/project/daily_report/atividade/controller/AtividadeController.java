package com.project.daily_report.atividade.controller;


import com.project.daily_report.atividade.domain.CategoriaAtividade;
import com.project.daily_report.atividade.dto.AtividadeRequest;
import com.project.daily_report.atividade.dto.AtividadeResponse;
import com.project.daily_report.atividade.service.AtividadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/atividades")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<AtividadeResponse> criar(@Valid @RequestBody AtividadeRequest request) {
        AtividadeResponse response = atividadeService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atividadeService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AtividadeResponse>> listar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Long empresaId,
            @RequestParam(required = false) String projeto,
            @RequestParam(required = false) CategoriaAtividade categoria,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        List<AtividadeResponse> atividades = atividadeService.buscarComFiltros(
                data, empresaId, projeto, categoria, dataInicial, dataFinal);

        return ResponseEntity.ok(atividades);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtividadeRequest request) {
        return ResponseEntity.ok(atividadeService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        atividadeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}