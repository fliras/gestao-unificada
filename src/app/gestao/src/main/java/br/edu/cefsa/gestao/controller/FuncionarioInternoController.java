package br.edu.cefsa.gestao.controller;

import br.edu.cefsa.gestao.dto.FuncionarioInternoForm;
import br.edu.cefsa.gestao.dto.ProjetoInternoVinculoForm;
import br.edu.cefsa.gestao.repository.CargoInternoRepository;
import br.edu.cefsa.gestao.repository.ContratoInternoRepository;
import br.edu.cefsa.gestao.repository.DepartamentoRepository;
import br.edu.cefsa.gestao.repository.PerfilAcessoRepository;
import br.edu.cefsa.gestao.repository.ProjetoInternoFuncionarioRepository;
import br.edu.cefsa.gestao.repository.ProjetoInternoRepository;
import br.edu.cefsa.gestao.service.FuncionarioInternoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/internos")
public class FuncionarioInternoController {

  private final FuncionarioInternoService service;
  private final PerfilAcessoRepository perfilRepo;
  private final CargoInternoRepository cargoInternoRepo;
  private final DepartamentoRepository departamentoRepo;
  private final ProjetoInternoRepository projetosRepo;
  private final ContratoInternoRepository contratoInternoRepo;
  private final ProjetoInternoFuncionarioRepository projetoFuncionarioRepo; 

  public FuncionarioInternoController(FuncionarioInternoService service,
                                      PerfilAcessoRepository perfilRepo,
                                      CargoInternoRepository cargoInternoRepo,
                                      DepartamentoRepository departamentoRepo,
                                      ProjetoInternoRepository projetosRepo,
                                      ContratoInternoRepository contratoInternoRepo,
                                      ProjetoInternoFuncionarioRepository projetoFuncionarioRepo) {
    this.service = service;
    this.perfilRepo = perfilRepo;
    this.cargoInternoRepo = cargoInternoRepo;
    this.departamentoRepo = departamentoRepo;
    this.projetosRepo = projetosRepo;
    this.contratoInternoRepo = contratoInternoRepo;
    this.projetoFuncionarioRepo = projetoFuncionarioRepo;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("lista", service.listarTodos());
    return "internos/list";
  }

  @GetMapping("/new")
  public String novoForm(Model model) {
    model.addAttribute("form", new FuncionarioInternoForm());
    model.addAttribute("perfis", perfilRepo.findAll());
    model.addAttribute("cargosInternos", cargoInternoRepo.findAll());
    model.addAttribute("departamentos", departamentoRepo.findAll());
    return "internos/form";
  }

  @PostMapping("/save")
  public String salvar(@Valid @ModelAttribute("form") FuncionarioInternoForm form,
                       BindingResult br, Model model, RedirectAttributes ra) {
    if (br.hasErrors()) {
      System.out.println("errado??");
      br.getAllErrors().forEach(e -> System.out.println("Erro: " + e));
      model.addAttribute("perfis", perfilRepo.findAll());
      model.addAttribute("cargosInternos", cargoInternoRepo.findAll());
      model.addAttribute("departamentos", departamentoRepo.findAll());
      return "internos/form";
    }
    try {
      service.criar(form);
      ra.addFlashAttribute("success", "Funcionário interno criado com sucesso");
      return "redirect:/internos";
    } catch (Exception e) {
      System.out.println("QUEIMA???? " + e.toString());
      br.reject("error.global", e.getMessage());
      model.addAttribute("perfis", perfilRepo.findAll());
      model.addAttribute("cargosInternos", cargoInternoRepo.findAll());
      model.addAttribute("departamentos", departamentoRepo.findAll());
      return "internos/form";
    }
  }

  @GetMapping("/delete/{matricula}")
  public String delete(@PathVariable String matricula, RedirectAttributes ra) {
    service.excluir(matricula);
    ra.addFlashAttribute("success", "Excluído");
    return "redirect:/internos";
  }

  @GetMapping("/{matricula}")
  public String view(@PathVariable String matricula, Model model) {
    var interno = service.buscarPorMatricula(matricula);
    var contratos = contratoInternoRepo.findAll();
    if (!contratos.isEmpty()) {
        model.addAttribute("cargoAtual", contratos.get(0).getCargoInterno().getCargo().getNome());
        model.addAttribute("salarioAtual", contratos.get(0).getCargoInterno().getSalarioBase());
        model.addAttribute("departamento", contratos.get(0).getDepartamento().getNome());
    }
    
    if (interno.isEmpty()) return "redirect:/internos";
    model.addAttribute("interno", interno.get());

    model.addAttribute("novoVinculo", new ProjetoInternoVinculoForm());
    model.addAttribute("todosProjetos", projetosRepo.findAll());
    model.addAttribute("projetosVinculados", projetoFuncionarioRepo.findByFuncionarioInternoMatricula(matricula));
    return "internos/view";
  }
}
