package br.edu.cefsa.gestao.repository;

import br.edu.cefsa.gestao.model.ProjetoInterno;
import br.edu.cefsa.gestao.model.ProjetoInternoFuncionario;
import br.edu.cefsa.gestao.model.ProjetoInternoFuncionarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetoInternoFuncionarioRepository extends JpaRepository<ProjetoInternoFuncionario, ProjetoInternoFuncionarioId> {

    List<ProjetoInternoFuncionario> findByFuncionarioInternoMatricula(String matricula);

    // ✅ Novo método para retornar apenas os projetos diretamente
    @Query("""
           SELECT pif.projetoInterno
           FROM ProjetoInternoFuncionario pif
           WHERE pif.funcionarioInterno.matricula = :matricula
           """)
    List<ProjetoInterno> findProjetosByFuncionarioMatricula(@Param("matricula") String matricula);
}
