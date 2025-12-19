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


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autoras", service.listar());
        return "autoras/listar";
    }


    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("autora", new Autora());
        return "autoras/cadastrar";
    }


    @PostMapping
    public String salvar(Autora autora) {
        service.salvar(autora);
        return "redirect:/autoras";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        return service.buscarPorId(id)
                .map(autora -> {
                    model.addAttribute("autora", autora);
                    return "autoras/editar";
                })
                .orElse("redirect:/autoras");
    }


    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Integer id, Autora autora) {
        autora.setId(id);
        service.salvar(autora);
        return "redirect:/autoras";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        service.deletar(id);
        return "redirect:/autoras";
    }
}
