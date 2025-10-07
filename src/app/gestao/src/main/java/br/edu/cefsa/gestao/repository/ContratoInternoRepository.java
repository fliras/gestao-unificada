package br.edu.cefsa.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.cefsa.gestao.model.ContratoInterno;

public interface ContratoInternoRepository extends JpaRepository<ContratoInterno, Integer> {}