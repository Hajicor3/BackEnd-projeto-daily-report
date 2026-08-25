package com.project.daily_report.atividade.domain;

import com.project.daily_report.empresa.domain.Empresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "atividade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private String titulo;

    @Column(length = 2000)
    private String descricao;

    private String encarregado;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private String projeto;

    @Enumerated(EnumType.STRING)
    private CategoriaAtividade categoria;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private Integer minutosTrabalhados;

    @Column(length = 1000)
    private String observacao;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
