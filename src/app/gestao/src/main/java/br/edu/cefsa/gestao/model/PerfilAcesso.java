package br.edu.cefsa.gestao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfis_acesso", schema = "GESTAODB")
public class PerfilAcesso {

    public Integer getIdPerfilAcesso() {
        return idPerfilAcesso;
    }

    public void setIdPerfilAcesso(Integer idPerfilAcesso) {
        this.idPerfilAcesso = idPerfilAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    @Id
    private Integer idPerfilAcesso;
    private String nome;
    private String descricao;
    // getters/setters
}
