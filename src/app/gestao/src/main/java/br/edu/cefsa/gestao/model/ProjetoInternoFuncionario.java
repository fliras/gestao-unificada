package br.edu.cefsa.gestao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projetos_internos_funcionarios", schema = "GESTAODB")
public class ProjetoInternoFuncionario {

    @EmbeddedId
    private ProjetoInternoFuncionarioId id;

    @ManyToOne
    @MapsId("idFuncionarioInterno")
    @JoinColumn(name = "id_funcionario_interno")
    private FuncionarioInterno funcionarioInterno;

    @ManyToOne
    @MapsId("idProjetoInterno")
    @JoinColumn(name = "id_projeto_interno")
    private ProjetoInterno projetoInterno;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    // getters e setters
    public ProjetoInternoFuncionarioId getId() { return id; }
    public void setId(ProjetoInternoFuncionarioId id) { this.id = id; }
    public FuncionarioInterno getFuncionarioInterno() { return funcionarioInterno; }
    public void setFuncionarioInterno(FuncionarioInterno funcionarioInterno) { this.funcionarioInterno = funcionarioInterno; }
    public ProjetoInterno getProjetoInterno() { return projetoInterno; }
    public void setProjetoInterno(ProjetoInterno projetoInterno) { this.projetoInterno = projetoInterno; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}
