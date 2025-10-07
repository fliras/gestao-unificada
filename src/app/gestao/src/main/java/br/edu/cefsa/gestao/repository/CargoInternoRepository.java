package br.edu.cefsa.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.cefsa.gestao.model.CargoInterno;

public interface CargoInternoRepository extends JpaRepository<CargoInterno, Integer> {}