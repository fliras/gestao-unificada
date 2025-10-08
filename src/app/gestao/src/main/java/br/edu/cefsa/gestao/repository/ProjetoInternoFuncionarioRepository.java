package br.edu.cefsa.gestao.repository;

import br.edu.cefsa.gestao.model.ProjetoInternoFuncionario;
import br.edu.cefsa.gestao.model.ProjetoInternoFuncionarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjetoInternoFuncionarioRepository extends JpaRepository<ProjetoInternoFuncionario, ProjetoInternoFuncionarioId> {
    List<ProjetoInternoFuncionario> findByFuncionarioInternoMatricula(String matricula);
}
