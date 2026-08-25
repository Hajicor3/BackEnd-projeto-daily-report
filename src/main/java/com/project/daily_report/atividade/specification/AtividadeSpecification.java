package com.project.daily_report.atividade.specification;

import com.project.daily_report.atividade.domain.Atividade;
import com.project.daily_report.atividade.domain.CategoriaAtividade;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;

public class AtividadeSpecification {

    public static Specification<Atividade> comData(LocalDate data) {
        return (root, query, criteriaBuilder) -> {
            if (data == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("data"), data);
        };
    }

    public static Specification<Atividade> comEmpresaId(Long empresaId) {
        return (root, query, criteriaBuilder) -> {
            if (empresaId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("empresa").get("id"), empresaId);
        };
    }

    public static Specification<Atividade> comProjeto(String projeto) {
        return (root, query, criteriaBuilder) -> {
            if (projeto == null || projeto.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("projeto"), projeto);
        };
    }

    public static Specification<Atividade> comCategoria(CategoriaAtividade categoria) {
        return (root, query, criteriaBuilder) -> {
            if (categoria == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("categoria"), categoria);
        };
    }

    public static Specification<Atividade> comPeriodo(LocalTime dataInicial, LocalTime dataFinal) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicial == null || dataFinal == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("horaInicio"), dataInicial),
                    criteriaBuilder.equal(root.get("horaFim"), dataFinal)
            );
        };
    }
}