package br.edu.cefsa.gestao.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ProjetoInternoVinculoForm {

    @NotNull
    private Integer idProjeto;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    // getters e setters
    public Integer getIdProjeto() { return idProjeto; }
    public void setIdProjeto(Integer idProjeto) { this.idProjeto = idProjeto; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}
