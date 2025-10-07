package br.edu.cefsa.gestao.controller;

import br.edu.cefsa.gestao.dto.FuncionarioInternoForm;
import br.edu.cefsa.gestao.repository.CargoInternoRepository;
import br.edu.cefsa.gestao.repository.DepartamentoRepository;
import br.edu.cefsa.gestao.repository.PerfilAcessoRepository;
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

  public FuncionarioInternoController(FuncionarioInternoService service,
                                      PerfilAcessoRepository perfilRepo,
                                      CargoInternoRepository cargoInternoRepo,
                                      DepartamentoRepository departamentoRepo) {
    this.service = service;
    this.perfilRepo = perfilRepo;
    this.cargoInternoRepo = cargoInternoRepo;
    this.departamentoRepo = departamentoRepo;
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
      System.out.println(e.getMessage());
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
    var opt = service.buscarPorMatricula(matricula);
    if (opt.isEmpty()) return "redirect:/internos";
    model.addAttribute("interno", opt.get());
    return "internos/view";
  }
}
