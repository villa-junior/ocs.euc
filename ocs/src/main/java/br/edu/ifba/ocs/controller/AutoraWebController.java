package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.service.AutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autoras")
public class AutoraWebController {

    @Autowired
    private AutoraService service;

    /* ================= LISTAR ================= */

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autoras", service.listar());
        return "autoras/listar";
    }

    /* ================= CADASTRAR ================= */

    @GetMapping("/cadastrar")
    public String cadastrar(
            @RequestParam(required = false) String voltar,
            Model model
    ) {
        model.addAttribute("autora", new Autora());
        model.addAttribute("voltar", voltar);
        return "autoras/cadastrar";
    }

    @PostMapping
    public String salvar(
            @ModelAttribute Autora autora,
            @RequestParam(required = false) String voltar
    ) {
        service.salvar(autora);

        if (voltar != null && !voltar.isBlank()) {
            return "redirect:" + voltar;
        }

        return "redirect:/autoras";
    }
}
