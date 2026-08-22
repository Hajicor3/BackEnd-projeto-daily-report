package com.project.daily_report.empresa.service;

import com.project.daily_report.empresa.domain.Empresa;
import com.project.daily_report.empresa.dto.EmpresaRequest;
import com.project.daily_report.empresa.dto.EmpresaResponse;
import com.project.daily_report.empresa.exception.EmpresaNaoEncontradaException;
import com.project.daily_report.empresa.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaResponse criar(EmpresaRequest request) {
        Empresa empresa = Empresa.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();

        Empresa salva = empresaRepository.save(empresa);

        return toResponse(salva);
    }

    public List<EmpresaResponse> listar() {
        return empresaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EmpresaResponse buscarPorId(Long id) {
        Empresa empresa = buscarEntidadePorId(id);
        return toResponse(empresa);
    }

    public EmpresaResponse atualizar(Long id, EmpresaRequest request) {
        Empresa empresa = buscarEntidadePorId(id);

        empresa.setNome(request.nome());
        empresa.setDescricao(request.descricao());
        empresa.setAtualizadoEm(LocalDateTime.now());

        Empresa atualizada = empresaRepository.save(empresa);

        return toResponse(atualizada);
    }

    public void excluir(Long id) {
        Empresa empresa = buscarEntidadePorId(id);
        empresaRepository.delete(empresa);
    }

    private Empresa buscarEntidadePorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EmpresaNaoEncontradaException(id));
    }

    private EmpresaResponse toResponse(Empresa empresa) {
        return new EmpresaResponse(
                empresa.getId(),
                empresa.getNome(),
                empresa.getDescricao(),
                empresa.getCriadoEm(),
                empresa.getAtualizadoEm()
        );
    }
}
