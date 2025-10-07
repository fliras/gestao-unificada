/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.gestao.service;

import br.edu.cefsa.gestao.dto.FuncionarioInternoForm;
import br.edu.cefsa.gestao.repository.CargoInternoRepository;
import br.edu.cefsa.gestao.repository.ContratoInternoRepository;
import br.edu.cefsa.gestao.repository.DepartamentoRepository;
import br.edu.cefsa.gestao.repository.FuncionarioInternoRepository;
import br.edu.cefsa.gestao.repository.FuncionarioRepository;
import br.edu.cefsa.gestao.repository.PerfilAcessoRepository;
import br.edu.cefsa.gestao.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.edu.cefsa.gestao.model.*;

@Service
@Transactional
public class FuncionarioInternoService {

  private final UsuarioRepository usuarioRepo;
  private final PerfilAcessoRepository perfilRepo;
  private final FuncionarioRepository funcionarioRepo;
  private final FuncionarioInternoRepository internoRepo;
  private final CargoInternoRepository cargoInternoRepo;
  private final DepartamentoRepository departamentoRepo;
  private final ContratoInternoRepository contratoRepo;
  private final BCryptPasswordEncoder encoder;

  public FuncionarioInternoService(UsuarioRepository usuarioRepo,
                                   PerfilAcessoRepository perfilRepo,
                                   FuncionarioRepository funcionarioRepo,
                                   FuncionarioInternoRepository internoRepo,
                                   CargoInternoRepository cargoInternoRepo,
                                   DepartamentoRepository departamentoRepo,
                                   ContratoInternoRepository contratoRepo,
                                   BCryptPasswordEncoder encoder) {
    this.usuarioRepo = usuarioRepo;
    this.perfilRepo = perfilRepo;
    this.funcionarioRepo = funcionarioRepo;
    this.internoRepo = internoRepo;
    this.cargoInternoRepo = cargoInternoRepo;
    this.departamentoRepo = departamentoRepo;
    this.contratoRepo = contratoRepo;
    this.encoder = encoder;
  }

  public List<FuncionarioInterno> listarTodos() {
    return internoRepo.findAll();
  }

  public Optional<FuncionarioInterno> buscarPorMatricula(String matricula) {
    return internoRepo.findById(matricula);
  }

  public FuncionarioInterno criar(FuncionarioInternoForm form) {
    // 1) criar Usuario
    PerfilAcesso perfil = perfilRepo.findById(form.getIdPerfilAcesso())
        .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado"));
    Usuario usuario = new Usuario();
    usuario.setEmail(form.getEmail());
    usuario.setHashSenha(encoder.encode(form.getSenha()));
    usuario.setPerfilAcesso(perfil);
    System.out.println("tendeu veinho?");
    usuario = usuarioRepo.save(usuario);

    // 2) criar Funcionario
    Funcionario func = new Funcionario();
    func.setMatricula(form.getMatricula());
    func.setNome(form.getNome());
    func.setCpf(form.getCpf());
    func.setUsuario(usuario);
    func = funcionarioRepo.save(func);

    // 3) criar FuncionarioInterno
    FuncionarioInterno interno = new FuncionarioInterno();
    interno.setFuncionario(func);
    interno.setMatricula(func.getMatricula());
    interno.setDataNascimento(form.getDataNascimento());
    interno = internoRepo.save(interno);

    // 4) criar ContratoInterno
    CargoInterno cargoInt = cargoInternoRepo.findById(form.getIdCargoInterno())
        .orElseThrow(() -> new IllegalArgumentException("Cargo interno não encontrado"));
    Departamento dept = departamentoRepo.findById(form.getIdDepartamento())
        .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado"));

    ContratoInterno contrato = new ContratoInterno();
    contrato.setFuncionarioInterno(interno);
    contrato.setCargoInterno(cargoInt);
    contrato.setDepartamento(dept);
    contrato.setDataInicio(form.getDataInicio());
    contrato.setDateFim(form.getDataFim());
    contratoRepo.save(contrato);

    return interno;
  }

  public void excluir(String matricula) {
    // delete contrato(s) -> interno -> funcionario -> usuario
    internoRepo.findById(matricula).ifPresent(interno -> {
      contratoRepo.findAll().stream()
        .filter(c -> c.getFuncionarioInterno().getMatricula().equals(matricula))
        .forEach(c -> contratoRepo.delete(c));
      internoRepo.delete(interno);
      Funcionario f = interno.getFuncionario();
      funcionarioRepo.delete(f);
      usuarioRepo.findById(f.getUsuario().getIdUsuario()).ifPresent(usuarioRepo::delete);
    });
  }

  // editar ... (similar a criar, mas atualiza registros existentes)
}

