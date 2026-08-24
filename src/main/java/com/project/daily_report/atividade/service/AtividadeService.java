package com.project.daily_report.atividade.service;

import com.project.daily_report.atividade.domain.Atividade;
import com.project.daily_report.atividade.domain.CategoriaAtividade;
import com.project.daily_report.atividade.dto.AtividadeRequest;
import com.project.daily_report.atividade.dto.AtividadeResponse;
import com.project.daily_report.atividade.exception.AtividadeNaoEncontradaException;
import com.project.daily_report.atividade.repository.AtividadeRepository;
import com.project.daily_report.empresa.domain.Empresa;
import com.project.daily_report.empresa.exception.EmpresaNaoEncontradaException;
import com.project.daily_report.empresa.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final EmpresaRepository empresaRepository;

    public AtividadeResponse criar(AtividadeRequest request) {
        Empresa empresa = buscarEmpresa(request.empresaId());

        Atividade atividade = Atividade.builder()
                .data(request.data())
                .titulo(request.titulo())
                .descricao(request.descricao())
                .empresa(empresa)
                .projeto(request.projeto())
                .categoria(request.categoria())
                .horaInicio(request.horaInicio())
                .horaFim(request.horaFim())
                .minutosTrabalhados(calcularMinutos(request.horaInicio(), request.horaFim()))
                .observacao(request.observacao())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();

        Atividade salva = atividadeRepository.save(atividade);

        return toResponse(salva);
    }

    public List<AtividadeResponse> listar() {
        return atividadeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AtividadeResponse buscarPorId(Long id) {
        return toResponse(buscarEntidadePorId(id));
    }

    public List<AtividadeResponse> filtrarPorData(LocalDate data) {
        return atividadeRepository.findByData(data)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AtividadeResponse> filtrarPorEmpresa(Long empresaId) {
        return atividadeRepository.findByEmpresaId(empresaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AtividadeResponse> filtrarPorProjeto(String projeto) {
        return atividadeRepository.findByProjeto(projeto)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AtividadeResponse> filtrarPorCategoria(CategoriaAtividade categoria) {
        return atividadeRepository.findByCategoria(categoria)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AtividadeResponse> filtrarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return atividadeRepository.findByDataBetween(dataInicial, dataFinal)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AtividadeResponse atualizar(Long id, AtividadeRequest request) {
        Atividade atividade = buscarEntidadePorId(id);
        Empresa empresa = buscarEmpresa(request.empresaId());

        atividade.setData(request.data());
        atividade.setTitulo(request.titulo());
        atividade.setDescricao(request.descricao());
        atividade.setEmpresa(empresa);
        atividade.setProjeto(request.projeto());
        atividade.setCategoria(request.categoria());
        atividade.setHoraInicio(request.horaInicio());
        atividade.setHoraFim(request.horaFim());
        atividade.setMinutosTrabalhados(calcularMinutos(request.horaInicio(), request.horaFim()));
        atividade.setObservacao(request.observacao());
        atividade.setAtualizadoEm(LocalDateTime.now());

        Atividade atualizada = atividadeRepository.save(atividade);

        return toResponse(atualizada);
    }

    public void excluir(Long id) {
        Atividade atividade = buscarEntidadePorId(id);
        atividadeRepository.delete(atividade);
    }

    private Integer calcularMinutos(java.time.LocalTime horaInicio, java.time.LocalTime horaFim) {
        long minutosInicio = horaInicio.toSecondOfDay() / 60;
        long minutosFim = horaFim.toSecondOfDay() / 60;

        // Se fim < início, adiciona minutos de um dia (24h = 1440 min)
        if (minutosFim < minutosInicio) {
            minutosFim += 24 * 60;
        }

        return (int) (minutosFim - minutosInicio);
    }

    private Atividade buscarEntidadePorId(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new AtividadeNaoEncontradaException(id));
    }

    private Empresa buscarEmpresa(Long empresaId) {
        return empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EmpresaNaoEncontradaException(empresaId));
    }

    private AtividadeResponse toResponse(Atividade atividade) {
        return new AtividadeResponse(
                atividade.getId(),
                atividade.getData(),
                atividade.getTitulo(),
                atividade.getDescricao(),
                atividade.getEmpresa().getId(),
                atividade.getEmpresa().getNome(),
                atividade.getProjeto(),
                atividade.getCategoria(),
                atividade.getHoraInicio(),
                atividade.getHoraFim(),
                atividade.getMinutosTrabalhados(),
                atividade.getObservacao(),
                atividade.getCriadoEm(),
                atividade.getAtualizadoEm()
        );
    }
}
