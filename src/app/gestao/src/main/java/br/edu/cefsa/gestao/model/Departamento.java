package br.edu.cefsa.gestao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamentos", schema = "GESTAODB")
public class Departamento {

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idDepartamento;
  private String nome;
  // getters/setters
}
