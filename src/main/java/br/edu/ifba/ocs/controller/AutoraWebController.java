package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Autora;
import br.edu.ifba.ocs.service.AutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/autoras")
public class AutoraWebController {

    @Autowired
    private AutoraService service;



    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("autoras", service.listar());
        return "autoras/listar";
    }



    @GetMapping("/cadastrar")
    public String cadastrar(
            @RequestParam(required = false) String voltar,
            Model model
    ) {
        model.addAttribute("autora", new Autora());
        model.addAttribute("voltar", voltar);
        return "autoras/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model) {

        Autora autora = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Autora não encontrada: " + id));

        model.addAttribute("autora", autora);
        return "autoras/editar";
    }

    @PostMapping("/editar/{id}")
    public String processarEdicao(@PathVariable UUID id, @ModelAttribute Autora autora) {
        autora.setId(id);
        service.salvar(autora);
        return "redirect:/autoras/listar";
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

        return "redirect:/autoras/listar";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id, Model model) {
        try {
            service.deletar(id);
            return "redirect:/autoras/listar";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("autoras", service.listar());
            return "autoras/listar";
        }
    }

}
