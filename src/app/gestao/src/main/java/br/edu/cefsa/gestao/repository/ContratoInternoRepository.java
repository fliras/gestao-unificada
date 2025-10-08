package br.edu.cefsa.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.cefsa.gestao.model.ContratoInterno;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoInternoRepository extends JpaRepository<ContratoInterno, Integer> {
    @Override
    @EntityGraph(attributePaths = {
        "cargoInterno.cargo",
        "departamento"
    })
    List<ContratoInterno> findAll();
}