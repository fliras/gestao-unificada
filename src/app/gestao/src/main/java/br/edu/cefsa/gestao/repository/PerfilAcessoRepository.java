package br.edu.cefsa.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.cefsa.gestao.model.PerfilAcesso;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Integer> {}