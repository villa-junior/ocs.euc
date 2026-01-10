package br.edu.ifba.ocs.controller;

import br.edu.ifba.ocs.model.Legislacao;
import br.edu.ifba.ocs.service.LegislacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/legislacoes")
public class LegislacaoWebController {

    private final LegislacaoService service;

    public LegislacaoWebController(LegislacaoService service) {
        this.service = service;
    }


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("legislacoes", service.listarTodas());
        return "legislacao/listar";
    }


    @GetMapping("/cadastrar")
    public String cadastrarForm(Model model) {
        model.addAttribute("legislacao", new Legislacao());
        model.addAttribute("hoje", LocalDate.now());
        return "legislacao/cadastrar";
    }



    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable UUID id, Model model) {
        model.addAttribute("legislacao", service.buscarPorId(id));
        return "legislacao/cadastrar";
    }


    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Legislacao legislacao) {
        service.salvar(legislacao);
        return "redirect:/legislacoes";
    }


    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        service.excluir(id);
        return "redirect:/legislacoes";
    }
}
