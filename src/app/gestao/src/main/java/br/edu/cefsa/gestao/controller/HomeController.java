package br.edu.cefsa.gestao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // retorna o nome da view (templates/index.html)
    }
}
