package br.edu.cefsa.gestao.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "projetos_internos", schema = "GESTAODB")
public class ProjetoInterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjeto;

    private String nome;

    @OneToMany(mappedBy = "projetoInterno")
    private Set<ProjetoInternoFuncionario> funcionariosVinculados;

    // getters e setters
    public Integer getIdProjeto() { return idProjeto; }
    public void setIdProjeto(Integer idProjeto) { this.idProjeto = idProjeto; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Set<ProjetoInternoFuncionario> getFuncionariosVinculados() { return funcionariosVinculados; }
    public void setFuncionariosVinculados(Set<ProjetoInternoFuncionario> funcionariosVinculados) { this.funcionariosVinculados = funcionariosVinculados; }
}
