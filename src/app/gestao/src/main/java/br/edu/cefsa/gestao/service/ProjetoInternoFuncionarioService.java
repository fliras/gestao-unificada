package br.edu.cefsa.gestao.service;

import br.edu.cefsa.gestao.model.ProjetoInterno;
import br.edu.cefsa.gestao.repository.ProjetoInternoFuncionarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjetoInternoFuncionarioService {

    private final ProjetoInternoFuncionarioRepository repository;

    public ProjetoInternoFuncionarioService(ProjetoInternoFuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<ProjetoInterno> buscarProjetosPorMatricula(String matricula) {
        return repository.findProjetosByFuncionarioMatricula(matricula);
    }
}
