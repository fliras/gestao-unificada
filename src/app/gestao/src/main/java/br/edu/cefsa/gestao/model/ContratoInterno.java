package br.edu.cefsa.gestao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "contratos_internos", schema = "GESTAODB")
public class ContratoInterno {

    public Integer getIdContratoInterno() {
        return idContratoInterno;
    }

    public FuncionarioInterno getFuncionarioInterno() {
        return funcionarioInterno;
    }

    public void setFuncionarioInterno(FuncionarioInterno funcionarioInterno) {
        this.funcionarioInterno = funcionarioInterno;
    }

    public CargoInterno getCargoInterno() {
        return cargoInterno;
    }

    public void setCargoInterno(CargoInterno cargoInterno) {
        this.cargoInterno = cargoInterno;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDateFim() {
        return dateFim;
    }

    public void setDateFim(LocalDate dateFim) {
        this.dateFim = dateFim;
    }
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idContratoInterno;

  @ManyToOne
  @JoinColumn(name = "id_funcionario_interno", referencedColumnName = "matricula")
  private FuncionarioInterno funcionarioInterno;

  @ManyToOne
  @JoinColumn(name = "id_cargo_interno", referencedColumnName = "id_cargo_interno")
  private CargoInterno cargoInterno;

  @ManyToOne
  @JoinColumn(name = "id_departamento")
  private Departamento departamento;

  private LocalDate dataInicio;
  private LocalDate dateFim;
  // getters/setters
}
