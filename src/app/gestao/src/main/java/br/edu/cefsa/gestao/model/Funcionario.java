package br.edu.cefsa.gestao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios", schema = "GESTAODB")
public class Funcionario {
    
  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;
  // getters/setters
    
  @Id
  private String matricula; // varchar(10)
  private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  private String cpf;

  
  public String getMatricula() {
      return matricula;
  }
  
  public void setMatricula(String matricula) {
      this.matricula = matricula;
  }
}
