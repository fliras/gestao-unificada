package br.edu.cefsa.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.cefsa.gestao.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {}