package br.edu.cefsa.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.cefsa.gestao.model.FuncionarioInterno;

public interface FuncionarioInternoRepository extends JpaRepository<FuncionarioInterno, String> {}