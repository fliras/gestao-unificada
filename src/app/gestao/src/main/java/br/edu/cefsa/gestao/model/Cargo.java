package br.edu.cefsa.gestao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargos", schema = "GESTAODB")
public class Cargo {
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getIdCardo() {
        return idCargo;
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargo;
    private String nome;
    // getters/setters
}
