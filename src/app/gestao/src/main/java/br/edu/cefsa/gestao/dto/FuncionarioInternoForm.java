package br.edu.cefsa.gestao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class FuncionarioInternoForm {

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdPerfilAcesso(Integer idPerfilAcesso) {
        this.idPerfilAcesso = idPerfilAcesso;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setIdCargoInterno(Integer idCargoInterno) {
        this.idCargoInterno = idCargoInterno;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

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

