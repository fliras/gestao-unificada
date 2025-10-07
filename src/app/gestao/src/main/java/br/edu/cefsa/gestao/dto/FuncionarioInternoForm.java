package br.edu.cefsa.gestao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class FuncionarioInternoForm {

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Integer getIdPerfilAcesso() {
        return idPerfilAcesso;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getIdCargoInterno() {
        return idCargoInterno;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
  @NotBlank @Size(max=10)
  private String matricula;

  @NotBlank @Size(max=50)
  private String nome;

  @NotBlank @Size(min=11, max=11)
  private String cpf;

  @NotBlank @Email
  private String email;

  @NotBlank @Size(min=6)
  private String senha;

  @NotNull
  private Integer idPerfilAcesso; // select

  @NotNull
  private LocalDate dataNascimento;

  // Contrato
  @NotNull
  private Integer idCargoInterno;

  @NotNull
  private Integer idDepartamento;

  @NotNull
  private LocalDate dataInicio;

  @NotNull
  private LocalDate dataFim;

  // getters/setters
}

