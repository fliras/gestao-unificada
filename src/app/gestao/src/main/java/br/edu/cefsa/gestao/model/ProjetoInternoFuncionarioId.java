package br.edu.cefsa.gestao.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProjetoInternoFuncionarioId implements Serializable {

    private String idFuncionarioInterno;
    private Integer idProjetoInterno;

    // getters, setters, equals e hashCode
    public String getIdFuncionarioInterno() { return idFuncionarioInterno; }
    public void setIdFuncionarioInterno(String idFuncionarioInterno) { this.idFuncionarioInterno = idFuncionarioInterno; }
    public Integer getIdProjetoInterno() { return idProjetoInterno; }
    public void setIdProjetoInterno(Integer idProjetoInterno) { this.idProjetoInterno = idProjetoInterno; }

    @Override
    public boolean equals(Object o) { 
        if (this == o) return true; 
        if (!(o instanceof ProjetoInternoFuncionarioId)) return false; 
        ProjetoInternoFuncionarioId that = (ProjetoInternoFuncionarioId) o; 
        return idFuncionarioInterno.equals(that.idFuncionarioInterno) && idProjetoInterno.equals(that.idProjetoInterno); 
    }

    @Override
    public int hashCode() { 
        return idFuncionarioInterno.hashCode() + idProjetoInterno.hashCode(); 
    }
}
