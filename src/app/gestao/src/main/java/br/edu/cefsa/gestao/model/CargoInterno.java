package br.edu.cefsa.gestao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargos_internos", schema = "GESTAODB")
public class CargoInterno {

    public Integer getIdCargoInterno() {
        return idCargoInterno;
    }

    public void setIdCargoInterno(Integer idCargoInterno) {
        this.idCargoInterno = idCargoInterno;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
  @OneToOne
  @MapsId
  @JoinColumn(name = "id_cargo_interno")
  private Cargo cargo;
  // getters/setters
  
  @Id
  private Integer idCargoInterno;
  private Double salarioBase;


}
