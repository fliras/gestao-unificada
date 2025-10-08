package br.edu.cefsa.gestao.controller;

import br.edu.cefsa.gestao.model.ProjetoInternoFuncionario;
import br.edu.cefsa.gestao.dto.ProjetoInternoVinculoForm;
import br.edu.cefsa.gestao.service.ProjetoInternoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/internos/{matricula}/projetos")
public class ProjetoInternoController {

    private final ProjetoInternoService service;

    public ProjetoInternoController(ProjetoInternoService service) {
        this.service = service;
    }

    @GetMapping
    public String viewProjetos(@PathVariable String matricula, Model model) {
        List<ProjetoInternoFuncionario> projetosVinculados = service.listarProjetosDoFuncionario(matricula);
        model.addAttribute("projetosVinculados", projetosVinculados);
        model.addAttribute("novoVinculo", new ProjetoInternoVinculoForm());
        model.addAttribute("todosProjetos", service.listarTodosProjetos());
        return "internos/view";
    }

    @PostMapping("/vincular")
    public String vincularProjeto(@PathVariable String matricula,
                                  @Valid @ModelAttribute("novoVinculo") ProjetoInternoVinculoForm form,
                                  BindingResult br,
                                  RedirectAttributes ra) {
        if (br.hasErrors()) return "internos/view";

        service.vincularFuncionarioAProjeto(matricula, form);
        ra.addFlashAttribute("success", "Vínculo criado com sucesso");
        return "redirect:/internos/" + matricula;
    }
}
