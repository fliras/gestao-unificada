package br.edu.cefsa.gestao.service;

import br.edu.cefsa.gestao.model.*;
import br.edu.cefsa.gestao.repository.*;
import br.edu.cefsa.gestao.dto.ProjetoInternoVinculoForm;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjetoInternoService {

    private final ProjetoInternoRepository projetoRepo;
    private final ProjetoInternoFuncionarioRepository vinculoRepo;
    private final FuncionarioInternoRepository funcionarioRepo;

    public ProjetoInternoService(ProjetoInternoRepository projetoRepo,
                                 ProjetoInternoFuncionarioRepository vinculoRepo,
                                 FuncionarioInternoRepository funcionarioRepo) {
        this.projetoRepo = projetoRepo;
        this.vinculoRepo = vinculoRepo;
        this.funcionarioRepo = funcionarioRepo;
    }

    public List<ProjetoInterno> listarTodosProjetos() {
        return projetoRepo.findAll();
    }

    public List<ProjetoInternoFuncionario> listarProjetosDoFuncionario(String matricula) {
        return vinculoRepo.findByFuncionarioInternoMatricula(matricula);
    }

    public void vincularFuncionarioAProjeto(String matricula, ProjetoInternoVinculoForm form) {
        FuncionarioInterno interno = funcionarioRepo.findById(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário interno não encontrado"));

        ProjetoInterno projeto = projetoRepo.findById(form.getIdProjeto())
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        
        System.out.println("MAMA MINHA???????????");

        ProjetoInternoFuncionario vinculo = new ProjetoInternoFuncionario();
        ProjetoInternoFuncionarioId id = new ProjetoInternoFuncionarioId();
        id.setIdFuncionarioInterno(matricula);
        id.setIdProjetoInterno(projeto.getIdProjeto());

        vinculo.setId(id);
        vinculo.setFuncionarioInterno(interno);
        vinculo.setProjetoInterno(projeto);
        vinculo.setDataInicio(form.getDataInicio());
        vinculo.setDataFim(form.getDataFim());

        vinculoRepo.save(vinculo);
    }
}
